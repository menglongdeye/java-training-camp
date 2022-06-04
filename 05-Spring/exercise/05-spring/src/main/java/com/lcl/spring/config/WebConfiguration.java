package com.lcl.spring.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class WebConfiguration {
    private String realName = "lcl";
}
