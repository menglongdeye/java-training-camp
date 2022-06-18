package com.lcl.mysqldemo.annotation;

import com.lcl.mysqldemo.config.DataSourceConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {
    String value() default DataSourceConstants.DS_KEY_DB01;
}
