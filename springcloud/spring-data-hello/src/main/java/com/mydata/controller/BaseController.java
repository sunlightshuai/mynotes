package com.mydata.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller公众抽象类
 */
@RestController
public abstract class BaseController {

    @Value("${spring.application.name}")
    private String currentServiceName;


    public  String getCurrentServiceName(){
        return currentServiceName;
    }


}
