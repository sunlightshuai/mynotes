package com.myclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="spring-data-hello")
public interface FeignDemo {

    @GetMapping("/{serviceName}")
    public String invokeSelectAll(@PathVariable String serviceName);
}
