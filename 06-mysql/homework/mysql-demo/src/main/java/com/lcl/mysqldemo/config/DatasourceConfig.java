package com.lcl.mysqldemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DatasourceConfig {

    @Bean("db01")
    @ConfigurationProperties(prefix = "spring.datasource.db01")
    public DataSource db01DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("db02")
    @ConfigurationProperties(prefix = "spring.datasource.db02")
    public DataSource db02DataSource(){
        return DataSourceBuilder.create().build();
    }
}
