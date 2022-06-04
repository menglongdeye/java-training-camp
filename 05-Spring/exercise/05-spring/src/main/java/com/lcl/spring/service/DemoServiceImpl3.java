package com.lcl.spring.service;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

@Service
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DemoServiceImpl3 implements DemoService3 {
    @Override
    public String test()  {
        System.out.printf("=============service");
        return "test success";
    }
}
