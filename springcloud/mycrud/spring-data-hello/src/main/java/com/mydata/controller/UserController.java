package com.mydata.controller;

import com.mydata.service.MyService;
import com.mydata.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController extends BaseController {

    @Autowired
    private MyService myService;

    @RequestMapping(value="/{functionName}/{serviceName}")
    public String getObject(@PathVariable String functionName, @PathVariable String serviceName,@RequestBody String body){
        Map<String,Object> requestBody = JsonUtil.jsonToMap(body);
        String id = (String)requestBody.get("message");
        Object obj = myService.select(Integer.parseInt(id));
        return obj.toString();
    }

}
