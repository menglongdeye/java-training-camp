package com.lcl.myspring.homework.service.impl;

import com.lcl.myspring.homework.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String getStre(String name) {
        System.out.println("======== " + name + "===========");
        return "hello " + name;
    }
}
