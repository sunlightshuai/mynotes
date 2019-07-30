package com.myclient.controller.base;


import com.myclient.loadbalance.LoadBalancedRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

public class LoadBalancedController implements BaseController {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private Integer port;

    public Integer getPort(){
        return port;
    }

    @Value("${application.name}")
    private String currentServiceName;

    public String getCurrentServiceName(){
        return currentServiceName;
    }

    @Bean
    public ClientHttpRequestInterceptor requestInterceptor(){
        return new LoadBalancedRequestInterceptor();
    }

    @Bean
    @Autowired
    public Object customeizer(@Qualifier Collection<RestTemplate> restTemplates, ClientHttpRequestInterceptor requestInterceptor){
        restTemplates.forEach(r ->{
            r.setInterceptors(Arrays.asList(requestInterceptor));
        });
        return new Object();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getLbRestTemplate(ClientHttpRequestInterceptor requestInterceptor){
        return new RestTemplate();
    }

    public RestTemplate getRestTemplate (){
        return restTemplate;
    }



}
