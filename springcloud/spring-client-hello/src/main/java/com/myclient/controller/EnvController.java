package com.myclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EnvController {

    private final Environment environment;

    @Autowired
    public EnvController(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public List<String> getAllServices(){
        return  discoveryClient.getServices();
    }

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/service/instances/{serviceName}")
    public List<String> getAllServiceInstances(@PathVariable String serviceName){
        return  discoveryClient.getInstances(serviceName).stream().map(
                s->s.getServiceId()+"-"+s.getHost()+"-"+s.getPort()
        ).collect(Collectors.toList());
    }


}
