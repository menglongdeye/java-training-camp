package com.lcl.myspring.homework.controller;

import com.lcl.myspring.homework.dto.XxlJobInfoDTO;
import com.lcl.myspring.homework.service.XxlJobService;
import com.lcl.myspring.homework.utils.JasyptEncryptorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/xxl")
public class XxlJobController {
    @Autowired
    private XxlJobService xxlJobService;

    @RequestMapping("/info")
    public XxlJobInfoDTO getInfo(int id){
        return xxlJobService.getInfo(id);
    }

    public static void main(String[] args) {
        JasyptEncryptorUtils.decode("123");
    }
}
