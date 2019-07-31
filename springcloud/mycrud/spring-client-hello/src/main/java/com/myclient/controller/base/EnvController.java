package com.myclient.controller.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;

/**
 * 获取ZK服务基础Controller
 * @Date 20190731
 */
public abstract class EnvController extends BaseController{


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
