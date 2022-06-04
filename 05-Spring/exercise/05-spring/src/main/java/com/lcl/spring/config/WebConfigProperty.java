package com.lcl.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lcl")
@Data
public class WebConfigProperty {
    private int age = 20;
}
