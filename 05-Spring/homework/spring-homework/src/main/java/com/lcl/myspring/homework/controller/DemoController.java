package com.lcl.myspring.homework.controller;

import com.lcl.myspring.homework.service.DemoService;
import com.lcl.myspring.homework.utils.SpringBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    //@Value("${test.value}")
    private String testStr = "123";

    @Value("${main.value}")
    private String mainvalue;

    @GetMapping("/getstr")
    public String getStr(String name){
        ApplicationContext applicationContext = SpringBeanUtils.getApplicationContext();
        DemoService demoService = applicationContext.getBean(DemoService.class);
        System.out.println("demoService ====  " + demoService.getClass() + "====" + testStr + "====" + mainvalue);
        return demoService.getStre(name + "====" + testStr + "====" + mainvalue);
    }
}
