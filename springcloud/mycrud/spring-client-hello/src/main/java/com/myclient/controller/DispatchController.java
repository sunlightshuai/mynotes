package com.myclient.controller;

import com.myclient.controller.base.BaseController;
import com.myclient.feign.FeignDataServices;
import com.myclient.util.JsonUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@RestController
public class DispatchController extends BaseController {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    private FeignDataServices feignDataServices;

    @HystrixCommand(
            fallbackMethod = "errContent",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000")
            }
    )
    @RequestMapping("/{applicationName}/{functionName}/{serviceName}")
    public String dispatchInvoke( @PathVariable String applicationName, @PathVariable String functionName, @PathVariable String serviceName,@RequestBody String body){
        Future<String> future = executorService.submit(()->{
            return feignDataServices.invoke(functionName,serviceName,body);
        });
        String invokeResult = null;
        try {
            invokeResult = future.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            invokeResult = errContent(applicationName,functionName,serviceName,body);
        }
        return invokeResult;
    }

    public String errContent(String applicationName, String functionName, String serviceName,String body){
        Map<String,Object> errorMap = new HashMap<>();
        errorMap.put("applicationName",applicationName);
        errorMap.put("functionName",functionName);
        errorMap.put("serviceName",serviceName);
        errorMap.put("body",body);
        errorMap.put("error","errorCode");
        return JsonUtil.mapToJsonStr(errorMap);
    }

}

