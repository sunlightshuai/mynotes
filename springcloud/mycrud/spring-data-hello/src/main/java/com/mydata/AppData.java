package com.mydata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.mydata.mapper")
@EnableDiscoveryClient
public class AppData
{
    public static void main( String[] args )
    {
        new SpringApplicationBuilder(AppData.class).
                web(WebApplicationType.SERVLET).
                run(args);


    }
}
