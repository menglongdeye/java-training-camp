package com.lcl.boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lcl.boot")
@Data
public class ConfigProperty {
    private int id;
    private String name;
}
