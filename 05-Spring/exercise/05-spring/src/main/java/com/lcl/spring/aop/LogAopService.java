package com.lcl.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAopService {

    /**
     * 环绕通知
     */
    @Pointcut(value = "execution(* com.lcl.spring.service.*.*(..))")
    public void point(){

    }

    @Before(value = "point()")
    public void before(){
        System.out.println("========== BEFORE ========");
    }

    @AfterReturning(value = "point()")
    public void after(){
        System.out.println("======== AFTER ========");
    }

    @Around(value = "point()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("======== around before ========");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("======== around AFTER ========");
        return proceed;
    }

}
