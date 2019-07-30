package com.mydata.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.mydata.service.MyService;
import com.mydata.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class UserController extends BaseController {

    @Autowired
    private MyService myService;

    private final static Random random = new Random();

    @RequestMapping("getObject/{id}")
    public String getObject(@PathVariable int id){
        Object obj = myService.select(id);
        System.err.println(obj);
        return obj.toString();
    }

    @RequestMapping("/getId")
    public String getObject(@RequestParam String id,@RequestParam String name){
        Object obj = myService.select(1);
        System.err.println(obj);
        return obj.toString();
    }

    @RequestMapping("insertObject/{id}")
    public String insertObject(@PathVariable String id){
        Map param = JsonUtil.jsonToMap(id);
        System.out.println(param);
        myService.insert((HashMap) param);
        return "hello";
    }

    @RequestMapping("selectAll")
    public Object selectAll() throws InterruptedException {
        Object result = myService.selectAll();
        System.out.println(result);
        return result;

    }

}
