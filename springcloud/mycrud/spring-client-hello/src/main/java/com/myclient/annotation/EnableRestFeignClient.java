package com.myclient.annotation;


import com.myclient.registrar.RestFeignClientsRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RestFeignClientsRegistrar.class})
public @interface EnableRestFeignClient {

    Class<?>[] clients() default {};
}
