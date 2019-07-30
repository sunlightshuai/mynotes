package com.myclient.controller;

import com.myclient.controller.base.LoadBalancedController;
import com.myclient.feign.FeignDemo;
import com.myclient.feign.RestFeignDemo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.*;

@RestController
public class DispatchFeignController extends LoadBalancedController {

    @Autowired
    private FeignDemo feignDemo;

    @Autowired
    private RestFeignDemo restFeignDemo;

    @GetMapping("/feign/{serviceName}")
    public String invokeSelectAll(@PathVariable String serviceName){
        return feignDemo.invokeSelectAll(serviceName);
    }


    @GetMapping("/rest/getId")
    public String invokeDemoSelectAll(@RequestParam String id,@RequestParam String name){
        return restFeignDemo.invokeSelectAll(id,name);
    }

}

