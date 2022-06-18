package com.lcl.mysqldemo.service;

import com.lcl.mysqldemo.annotation.DS;
import com.lcl.mysqldemo.config.DataSourceConstants;
import com.lcl.mysqldemo.entity.db01.OrderInfo;
import com.lcl.mysqldemo.entity.db02.XxlJobInfo;
import com.lcl.mysqldemo.mapper.dynamic.OrderInfoMapper1;
import com.lcl.mysqldemo.mapper.dynamic.XxlJobInfoMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private OrderInfoMapper1 orderInfoMapper;
    @Autowired
    private XxlJobInfoMapper1 xxlJobInfoMapper;

    @DS
    public OrderInfo getOrderInfo(int key){
        return orderInfoMapper.selectByPrimaryKey(key);
    }

    @DS(DataSourceConstants.DS_KEY_DB01)
    public OrderInfo getOrderInfo2(int key){
        return orderInfoMapper.selectByPrimaryKey(key);
    }

    @DS(DataSourceConstants.DS_KEY_DB02)
    public XxlJobInfo getXxljobInfo(int key){
        return xxlJobInfoMapper.selectByPrimaryKey(key);
    }

}
