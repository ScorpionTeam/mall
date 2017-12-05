package com.scoprion.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by kunlun
 * @created on 2017/12/5.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
public @interface ApiVersion {

    //版本号
    int value();
}
