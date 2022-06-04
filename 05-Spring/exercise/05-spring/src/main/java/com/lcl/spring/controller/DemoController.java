package com.lcl.spring.controller;

import com.lcl.spring.aop.LogAopService;
import com.lcl.spring.service.*;
import com.lcl.spring.utils.SpringBeanUtils;
import com.lcl.spring.utils.SpringBeanUtils2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private DemoService2 demoService2;

    @Resource
    private DemoService3 demoService3;

    @RequestMapping("/test")
    public String test(){
        ApplicationContext applicationContext2 = SpringBeanUtils2.getApplicationContext();
        ApplicationContext applicationContext = SpringBeanUtils.getApplicationContext();
        //DemoService demoService = applicationContext.getBean(DemoService.class);
//        System.out.println("代理类===" + demoService.getClass());
//        System.out.println("继承关系===" + (demoService  instanceof DemoService));
//        System.out.println("继承关系===" + (demoService  instanceof DemoServiceImpl));
//
//        DemoService demoService = applicationContext.getBean(DemoService.class);
//        System.out.println("代理类===" + demoService.getClass());
//        System.out.println("继承关系===" + (demoService  instanceof DemoService));
//        System.out.println("继承关系===" + (demoService  instanceof DemoServiceImpl));

        DemoService3 demoService3 = applicationContext.getBean(DemoService3.class);
        System.out.println("代理类===" + demoService3.getClass());
        demoService3.test();
        System.out.println("继承关系===" + (demoService3  instanceof DemoService3));
        System.out.println("继承关系===" + (demoService3  instanceof DemoServiceImpl3));

        //DemoService2 demoService2 = applicationContext.getBean(DemoService2.class);
//        System.out.println("代理类===" + demoService2.getClass());
//        System.out.println("继承关系===" + (demoService2  instanceof DemoService));
//        System.out.println("继承关系===" + (demoService2  instanceof DemoService2));
//
//        LogAopService logAopService = applicationContext.getBean(LogAopService.class);
//        System.out.println("代理类===" + logAopService.getClass());
//        System.out.println("继承关系===" + (logAopService  instanceof LogAopService));

        Student student = applicationContext.getBean(Student.class);
        System.out.printf("student" + "-" + student.toString());
        return demoService3.test();
    }
}
