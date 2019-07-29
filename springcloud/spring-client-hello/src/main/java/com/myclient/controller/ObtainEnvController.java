package com.myclient.controller;

import com.myclient.controller.base.EnvController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ObtainEnvController extends EnvController {

    public ObtainEnvController(Environment environment) {
        super(environment);
    }

    @GetMapping("/services")
    public List<String> getAllServices(){
        return  getDiscoveryClient().getServices();
    }

    @GetMapping("/services/{serviceName}")
    public List<String> getAllServiceInstances(@PathVariable String serviceName){
        return  getDiscoveryClient().getInstances(serviceName).stream().map(
                // s->s.getServiceId()+":"+s.getHost()+":"+s.getPort()+"-"+s.getUri().toString()
                s->s.getServiceId()+":"+s.getUri().toString()
        ).collect(Collectors.toList());
    }

}
