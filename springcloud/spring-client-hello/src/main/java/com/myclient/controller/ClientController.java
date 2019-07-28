package com.myclient.controller;

import com.myclient.loadbalance.LoadBalancedRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

@RestController
public class ClientController {

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

    @Autowired
    @LoadBalanced
    private RestTemplate lbRestTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate getLbRestTemplate(ClientHttpRequestInterceptor requestInterceptor){
        return new RestTemplate();
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String currentServiceName;

    @GetMapping(value="/{serviceName}/{message}")
    public String lbInvokeSay(@PathVariable String serviceName, @PathVariable String message){
       // return  lbRestTemplate.getForObject(serviceName+"/"+message,String.class);
        return  lbRestTemplate.postForObject(serviceName+"/"+message,null,String.class);
    }

}

