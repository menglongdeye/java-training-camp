package com.lcl.mysqldemo.controller;

import com.alibaba.fastjson.JSON;
import com.lcl.mysqldemo.config.DataSourceConstants;
import com.lcl.mysqldemo.config.DynamicDataSourceContextHolder;
import com.lcl.mysqldemo.entity.db01.OrderInfo;
import com.lcl.mysqldemo.entity.db02.XxlJobInfo;
import com.lcl.mysqldemo.mapper.db01.OrderInfoMapper;
import com.lcl.mysqldemo.mapper.db02.XxlJobInfoMapper;
import com.lcl.mysqldemo.mapper.dynamic.OrderInfoMapper1;
import com.lcl.mysqldemo.mapper.dynamic.XxlJobInfoMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test2")
public class TestController2 {

    @Autowired
    private OrderInfoMapper1 orderInfoMapper;
    @Autowired
    private XxlJobInfoMapper1 xxlJobInfoMapper;

    @GetMapping("/03")
    public String test02(){
        Map<String, String> map = new HashMap<>();
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(1);
        map.put("orderInfo", JSON.toJSONString(orderInfo));
        DynamicDataSourceContextHolder.setContextKey(DataSourceConstants.DS_KEY_DB02);
        XxlJobInfo xxlJobInfo = xxlJobInfoMapper.selectByPrimaryKey(2);
        map.put("xxlJobInfo", JSON.toJSONString(xxlJobInfo));
        DynamicDataSourceContextHolder.removeContextKey();
        return JSON.toJSONString(map);
    }
}
