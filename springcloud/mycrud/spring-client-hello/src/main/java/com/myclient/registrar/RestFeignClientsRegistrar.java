package com.myclient.registrar;

import com.myclient.annotation.EnableRestFeignClient;
import com.myclient.annotation.RestFeignClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;


public class RestFeignClientsRegistrar  implements ImportBeanDefinitionRegistrar, BeanFactoryAware {


    private BeanFactory beanFactory;


    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        ClassLoader classLoader = annotationMetadata.getClass().getClassLoader();
        Map<String,Object> defaultAttrs = annotationMetadata.getAnnotationAttributes(EnableRestFeignClient.class.getName());

        Class[] clientClasses = (Class[])defaultAttrs.get("clients");


        Stream.of(clientClasses)
                .filter(Class::isInterface)
                .filter(interfaceClass ->
                        findAnnotation(interfaceClass, RestFeignClient.class)!=null)
                .forEach(
                        restFeignClientClass ->{
                            RestFeignClient restFeignClient = findAnnotation(restFeignClientClass, RestFeignClient.class);
                            String serviceName = restFeignClient.name();
                            // 过滤@RequestMappint方法
                            Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{restFeignClientClass}, new DemoInvocationHandler(serviceName,beanFactory) );

                            String beanName = "RestFeignClient."+serviceName;
                            BeanDefinitionBuilder beanDefinitionBuilder =
                                    BeanDefinitionBuilder.genericBeanDefinition(RestFeignClientsRegistrarBean.class);

                            beanDefinitionBuilder.addConstructorArgValue(restFeignClientClass);
                            beanDefinitionBuilder.addConstructorArgValue(proxy);
                            BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
                            beanDefinitionRegistry.registerBeanDefinition(beanName,beanDefinition);

                        }
        );


    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    private static class RestFeignClientsRegistrarBean implements FactoryBean{


        private Object proxy;

        public RestFeignClientsRegistrarBean(Class<?> restClientClass,Object proxy) {
            this.restClientClass = restClientClass;
            this.proxy = proxy;
        }

        private Class<?> restClientClass;
        @Override
        public Object getObject() throws Exception {
            return proxy;
        }

        @Override
        public Class<?> getObjectType() {
            return restClientClass;
        }
    }

}




