package com.lcl.spring.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService2{
    public String test()  {
        System.out.printf("=============service");



        return "test success";
    }
}
