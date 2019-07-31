package com.mydata.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * 将拦截器配置回调中
 * @date 20190729
 */
@Configuration
public class DisposeWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截的管理器 拦截的对象会进入这个类中进行判断
        InterceptorRegistration registration = registry.addInterceptor(new RequestHandlerInterceptor());
        // 所有路径都被拦截
        registration.addPathPatterns("/**");
        // 添加不拦截路径
        registration.excludePathPatterns("/","/login","/error","/static/**","/logout");

    }
}
