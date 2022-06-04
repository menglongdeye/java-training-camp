package com.lcl.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WebConfiguration.class)
@EnableConfigurationProperties(WebConfigProperty.class)
public class AutoWebConfiguration {

    @Autowired
    WebConfigProperty property;
    @Autowired
    WebConfiguration configuration;

    @Bean
    public WebInfo createWebInfo(){
        return new WebInfo(configuration.getRealName() + "----" + property.getAge());
    }
}
