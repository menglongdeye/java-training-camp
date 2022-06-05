package com.lcl.myspring.homework.controller;

import com.alibaba.fastjson.JSON;
import com.lcl.myspring.homework.dto.XxlJobInfoDTO;
import com.lcl.myspring.homework.service.XxlJobService;
import com.lcl.myspring.homework.utils.JasyptEncryptorUtils;
import com.lcl.myspring.homework.utils.SpringBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/v1/xxl")
public class XxlJobController {
    @Autowired
    private XxlJobService xxlJobService;


    @RequestMapping("/info")
    public XxlJobInfoDTO getInfo(int id){
        XxlJobInfoDTO xxlJobInfoDTO = xxlJobService.getInfo(id);
        ApplicationContext applicationContext = SpringBeanUtils.getApplicationContext();
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println("xxlJobInfoDTO====" + JSON.toJSONString(xxlJobInfoDTO));
        System.out.println("dataSource====" + dataSource);
        return xxlJobInfoDTO;
    }

    @RequestMapping("jdbc")
    public XxlJobInfoDTO getInfoByJDBC(int id){
        ApplicationContext applicationContext = SpringBeanUtils.getApplicationContext();
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println("dataSource====" + JSON.toJSONString(dataSource));
        return xxlJobService.getInfoByJDBC(id);
    }

}
