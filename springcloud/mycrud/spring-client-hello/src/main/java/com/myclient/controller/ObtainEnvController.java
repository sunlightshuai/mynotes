package com.myclient.controller;

import com.myclient.controller.base.EnvController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ObtainEnvController extends EnvController {

    public ObtainEnvController(Environment environment) {
        super(environment);
    }

    @RequestMapping("/services")
    public List<String> getAllServices(){
        return  getDiscoveryClient().getServices();
    }

    @RequestMapping("/services/{applicationName}")
    public List<String> getAllServiceInstances(@PathVariable String applicationName){
        return  getDiscoveryClient().getInstances(applicationName).stream().map(
                s->s.getServiceId()+":"+s.getUri().toString()
        ).collect(Collectors.toList());
    }

}
