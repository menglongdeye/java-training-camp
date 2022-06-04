package com.lcl.spring.service;

import com.lcl.spring.utils.SpringBeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

@Service
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class DemoServiceImpl implements DemoService{
    @Override
    public String test()  {
        System.out.printf("=============service");



        return "test success";
    }
}
