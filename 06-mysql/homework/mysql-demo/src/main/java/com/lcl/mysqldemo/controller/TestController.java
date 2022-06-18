package com.lcl.mysqldemo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcl.mysqldemo.entity.db01.OrderInfo;
import com.lcl.mysqldemo.entity.db02.XxlJobInfo;
import com.lcl.mysqldemo.mapper.db01.OrderInfoMapper;
import com.lcl.mysqldemo.mapper.db02.XxlJobInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private XxlJobInfoMapper xxlJobInfoMapper;

    @GetMapping("/db")
    public String testDb(@PathVariable("db")String db){
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(1);
        XxlJobInfo xxlJobInfo = xxlJobInfoMapper.selectByPrimaryKey(2);
        return "OK";
    }

    @GetMapping("/03")
    public String test02(){
        Map<String, String> map = new HashMap<>();
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(1);
        XxlJobInfo xxlJobInfo = xxlJobInfoMapper.selectByPrimaryKey(2);
        map.put("orderInfo", JSON.toJSONString(orderInfo));
        map.put("xxlJobInfo", JSON.toJSONString(xxlJobInfo));
        return JSON.toJSONString(map);
    }
}
