package com.lcl.mysqldemo.controller;

import com.alibaba.fastjson.JSON;
import com.lcl.mysqldemo.config.DataSourceConstants;
import com.lcl.mysqldemo.config.DynamicDataSourceContextHolder;
import com.lcl.mysqldemo.entity.db01.OrderInfo;
import com.lcl.mysqldemo.entity.db02.XxlJobInfo;
import com.lcl.mysqldemo.mapper.dynamic.OrderInfoMapper1;
import com.lcl.mysqldemo.mapper.dynamic.XxlJobInfoMapper1;
import com.lcl.mysqldemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test3")
public class TestController3 {

    @Autowired
    private TestService testService;

    @GetMapping("/03")
    public String test02(){
        Map<String, String> map = new HashMap<>();
        OrderInfo orderInfo = testService.getOrderInfo(1);
        map.put("orderInfo", JSON.toJSONString(orderInfo));
        OrderInfo orderInfo2 = testService.getOrderInfo2(1);
        map.put("orderInfo2", JSON.toJSONString(orderInfo2));
        XxlJobInfo xxlJobInfo = testService.getXxljobInfo(2);
        map.put("xxlJobInfo", JSON.toJSONString(xxlJobInfo));
        DynamicDataSourceContextHolder.removeContextKey();
        return JSON.toJSONString(map);
    }
}
