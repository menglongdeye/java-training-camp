package com.lcl.mysqldemo.annotation.aspect;

import com.lcl.mysqldemo.annotation.DS;
import com.lcl.mysqldemo.config.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class DynamicDatasourceAspect {

    @Pointcut(value = "@annotation(com.lcl.mysqldemo.annotation.DS)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String dsKey = getDsAnnotation(joinPoint).value();
        try{
            DynamicDataSourceContextHolder.setContextKey(dsKey);
            return joinPoint.proceed();
        }finally {
            DynamicDataSourceContextHolder.removeContextKey();
        }
    }


    private DS getDsAnnotation(ProceedingJoinPoint joinPoint){
        Class<?> targetClass = joinPoint.getTarget().getClass();
        DS annotation = targetClass.getAnnotation(DS.class);
        if(Objects.nonNull(annotation)){
            return annotation;
        }else {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            return signature.getMethod().getAnnotation(DS.class);
        }
    }
}
