package com.myclient.loadbalance;

import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

public class LoadBalancedRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final String HTTPS = "https://";

    private static final String HTTP = "http://";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public RoundRobinRule getRouteRobinRule() {
        return new RoundRobinRule();
    }


    private volatile Map<String, Set<String>> targetUrlsCache = new HashMap<>();

    @Scheduled(fixedRate = 10*1000)
    public void updateTargetUrlsCache(){
        Map<String,Set<String>> oldTargetUrlsCache = this.targetUrlsCache;
        Map<String,Set<String>> newTargetUrlsCache = new HashMap<String,Set<String>>();
        discoveryClient.getServices().forEach(serviceName->{
                    List<ServiceInstance> serviceInstance = discoveryClient.getInstances(serviceName);
                    Set<String> newTargetUrls = serviceInstance.stream().map(s -> s.isSecure()?
                            HTTPS+s.getHost()+":"+s.getPort():
                            HTTP+s.getHost()+":"+s.getPort()).collect(Collectors.toSet());

                    newTargetUrlsCache.put(serviceName,newTargetUrls);
                }
        );
        this.targetUrlsCache = newTargetUrlsCache;
    }


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        URI requestURI = request.getURI();
        String path = requestURI.getPath();
        String[] parts = StringUtils.split(path.substring(1),"/");

        String serviceName = parts[0];
        String uri = parts[1];

        List<String> targetUrls = new LinkedList<>(targetUrlsCache.get(serviceName));
        int size = targetUrls.size();
        int index = new Random().nextInt(size);
        String targetUrl = targetUrls.get(index);

        String actualURL = targetUrl + "/" + uri + "?"+ requestURI.getQuery();

        URL url = new URL(actualURL);

        URLConnection urlConnection = url.openConnection();

        InputStream responseBody = urlConnection.getInputStream();

        return new SimpleClientHttpResponse(responseBody,new HttpHeaders());
    }



    private static class SimpleClientHttpResponse implements ClientHttpResponse {

        private InputStream body;

        private HttpHeaders httpHeaders;

        public SimpleClientHttpResponse(InputStream body, HttpHeaders httpHeaders){
            this.body = body;
            this.httpHeaders = httpHeaders;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.OK;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return 200;
        }

        @Override
        public String getStatusText() throws IOException {
            return "OK";
        }

        @Override
        public void close() {

        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            httpHeaders.set(HttpHeaders.CONTENT_TYPE,"application/json;charset=utf-8");
            return httpHeaders;
        }
    }
}
