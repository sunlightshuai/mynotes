package com.myclient.controller.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;

public class EnvController implements BaseController{


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

    private final Environment environment;

    @Autowired
    public EnvController(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    private DiscoveryClient discoveryClient;


    public DiscoveryClient getDiscoveryClient(){
        return discoveryClient;
    }



}
