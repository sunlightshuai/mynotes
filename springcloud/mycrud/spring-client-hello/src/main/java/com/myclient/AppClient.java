package com.myclient;

import com.myclient.feign.FeignDataServices;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients(clients = FeignDataServices.class)
public class AppClient
{
    public static void main( String[] args )
    {
        new SpringApplicationBuilder(AppClient.class).web(WebApplicationType.SERVLET).run(args);
    }
}
