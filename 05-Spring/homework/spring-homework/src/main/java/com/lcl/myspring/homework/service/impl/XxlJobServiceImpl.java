package com.lcl.myspring.homework.service.impl;

import com.alibaba.fastjson.JSON;
import com.lcl.myspring.homework.dto.XxlJobInfoDTO;
import com.lcl.myspring.homework.entity.XxlJobInfo;
import com.lcl.myspring.homework.mapper.XxlJobInfoMapper;
import com.lcl.myspring.homework.service.XxlJobService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class XxlJobServiceImpl implements XxlJobService {

    private final XxlJobInfoMapper xxlJobInfoMapper;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public XxlJobInfoDTO getInfo(int id) {
        XxlJobInfo xxlJobInfo = xxlJobInfoMapper.selectByPrimaryKey(id);
        XxlJobInfoDTO xxlJobInfoDTO = new XxlJobInfoDTO();
        BeanUtils.copyProperties(xxlJobInfo, xxlJobInfoDTO);
        return xxlJobInfoDTO;
    }

    @Override
    public XxlJobInfoDTO getInfoByJDBC(int id) {
        String sql = "select * from xxl_job_info where id = " + id;
        List<XxlJobInfoDTO> xxlJobInfoDTOList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<XxlJobInfoDTO>(XxlJobInfoDTO.class));
        XxlJobInfoDTO xxlJobInfoDTO = new XxlJobInfoDTO();
        if(null!=xxlJobInfoDTOList && xxlJobInfoDTOList.size()>0){
            xxlJobInfoDTO = xxlJobInfoDTOList.get(0);
        }
        return xxlJobInfoDTO;
    }
}
