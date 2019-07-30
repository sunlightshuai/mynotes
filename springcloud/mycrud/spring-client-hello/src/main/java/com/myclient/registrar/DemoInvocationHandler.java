package com.myclient.registrar;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class DemoInvocationHandler implements InvocationHandler {


    private String serviceName;

    private BeanFactory beanFactory;

    public DemoInvocationHandler(String serviceName, BeanFactory beanFactory){
        this.beanFactory = beanFactory;
        this.serviceName = serviceName;
    }
    // ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
        if (null != getMapping){
            String[] uri = getMapping.value();
            StringBuilder urlBuilder = new StringBuilder(serviceName).append("/").append(uri[0]);
            int count = method.getParameterCount();

            Parameter[] parameters  = method.getParameters();
            // String [] paramNames = parameterNameDiscoverer.getParameterNames(method);
            Class<?> [] paramTypes = method.getParameterTypes();

            Annotation[][] annotations = method.getParameterAnnotations();

            StringBuffer queryString = new StringBuffer();

            for (int i = 0;i<count;i++){
                Annotation[] paramAnnotations = annotations[i];
                Class<?> paramType = paramTypes[i];

                for (int j = 0;j< paramAnnotations.length;j++){
                    // RequestParam requestParam = paramType.getAnnotation(RequestParam.class);
                    RequestParam requestParam = (RequestParam)paramAnnotations[j];
                    if (null != requestParam){
                        // String paramName = paramNames[i];
                        String paramName = parameters[i].getName();
                        String requestParamName = StringUtils.hasText(requestParam.value())?requestParam.value():paramName;
                        String requestParamValue = String.class.equals(paramType)?(String)args[i]:String.valueOf(args[i]);
                        queryString.append("&").append(requestParamName).append("=").append(requestParamValue);

                    }
                }
            }
            String queryStr = queryString.toString();
            if (StringUtils.hasText(queryStr)){
                urlBuilder.append("?").append(queryStr);
            }
            String url = urlBuilder.toString();
            RestTemplate restTemplate = beanFactory.getBean("getLbRestTemplate",RestTemplate.class);

            return restTemplate.getForObject(url,method.getReturnType());
        }
        return null;
    }
}
