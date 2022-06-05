package com.lcl.myspring.homework.service.impl;

import com.lcl.myspring.homework.dto.XxlJobInfoDTO;
import com.lcl.myspring.homework.entity.XxlJobInfo;
import com.lcl.myspring.homework.mapper.XxlJobInfoMapper;
import com.lcl.myspring.homework.service.XxlJobService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class XxlJobServiceImpl implements XxlJobService {

    private final XxlJobInfoMapper xxlJobInfoMapper;

    @Override
    public XxlJobInfoDTO getInfo(int id) {
        XxlJobInfo xxlJobInfo = xxlJobInfoMapper.selectByPrimaryKey(id);
        XxlJobInfoDTO xxlJobInfoDTO = new XxlJobInfoDTO();
        BeanUtils.copyProperties(xxlJobInfo, xxlJobInfoDTO);
        return xxlJobInfoDTO;
    }
}
