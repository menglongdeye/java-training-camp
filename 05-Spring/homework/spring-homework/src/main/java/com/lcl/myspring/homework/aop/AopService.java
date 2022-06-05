//package com.lcl.myspring.homework.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class AopService {
//
//    @Pointcut(value = "execution(* com.lcl.myspring.homework.service..*.*(..))")
//    public void pointcut(){
//
//    }
//
//    @Before(value = "pointcut()")
//    public void before(){
//        System.out.println("======= before ======");
//    }
//
//    @After(value = "pointcut()")
//    public void after(){
//        System.out.println("========= after ========");
//    }
//
//    @Around(value = "pointcut()")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//        System.out.println("====== around before =======");
//        Object obj =  proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
//        System.out.println("======== around after ========");
//        return obj;
//    }
//}
