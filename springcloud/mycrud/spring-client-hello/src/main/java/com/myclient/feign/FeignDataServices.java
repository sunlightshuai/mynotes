package com.myclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name="${target.server.application.name}")
public interface FeignDataServices {

    @RequestMapping(value="/{functionName}/{serviceName}")
    public String invoke(@PathVariable String functionName, @PathVariable String serviceName,@RequestBody String body);
}
