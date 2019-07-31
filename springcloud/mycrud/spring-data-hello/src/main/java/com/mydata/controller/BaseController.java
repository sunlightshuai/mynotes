package com.mydata.controller;

import com.mydata.util.JsonUtil;
import io.micrometer.core.instrument.util.IOUtils;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller公众抽象类
 */
public abstract class BaseController {

    @Value("${spring.application.name}")
    private String currentServiceName;

    public  String getCurrentServiceName(){
        return currentServiceName;
    }

    public Map<String,Object> obtainRequestBody(HttpServletRequest httpServletRequest){
        Map<String,Object> resultMap = new HashMap<>();
        String contentStr = "";
        try {
            InputStream is = httpServletRequest.getInputStream();
            contentStr = IOUtils.toString(is, Charset.forName("utf-8"));
            if (!StringUtils.isEmpty(contentStr)){
                resultMap =  JsonUtil.jsonToMap(contentStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


}
