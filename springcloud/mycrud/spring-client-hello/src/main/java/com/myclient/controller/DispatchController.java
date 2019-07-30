package com.myclient.controller;

import com.myclient.controller.base.LoadBalancedController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class DispatchController extends LoadBalancedController {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @HystrixCommand(
            fallbackMethod = "errContent",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000")
            }
    )
    @GetMapping(value="/{applicationName}/{serviceName}")
    public String invokeDispatch(@PathVariable String applicationName, @PathVariable String serviceName){

        Future<String> future = executorService.submit(()->{
            return doInvokeDispatch( applicationName,  serviceName);
        });
        String invokeResult = null;
        try {
            invokeResult = future.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            invokeResult = errContent(applicationName,serviceName);
        }
        return invokeResult;

    }

    private String doInvokeDispatch(String applicationName, String serviceName){
        return  getRestTemplate().postForObject(applicationName+"/"+serviceName,null,String.class);
    }

    public String errContent(String applicationName,String serviceName){
        return "get " + serviceName + " err.";
    }

}

