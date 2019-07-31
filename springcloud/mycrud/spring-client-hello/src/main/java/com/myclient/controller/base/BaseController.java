package com.myclient.controller.base;

import org.springframework.beans.factory.annotation.Value;


/**
 * controller层顶层接口
 * @Date 20190729
 */
public abstract class BaseController {

    @Value("${server.port}")
    private Integer port;

    public Integer getPort(){
        return port;
    }

    @Value("${application.name}")
    private String currentServiceName;

    public String getCurrentServiceName(){
        return currentServiceName;
    }


}
