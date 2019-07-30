package com.myclient.feign;

import com.myclient.annotation.RestFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestFeignClient(name="spring-data-hello")
public interface RestFeignDemo {

    @GetMapping("/getId")
    public String invokeSelectAll(@RequestParam String id,@RequestParam String name);
}
