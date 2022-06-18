package com.lcl.mysqldemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:jdbc.properties")
@MapperScan(basePackages = "com.lcl.mysqldemo.mapper.dynamic")
public class DynamicDataSourceConfig {

    @Bean(DataSourceConstants.DS_KEY_DB01)
    @ConfigurationProperties(prefix = "spring.datasource.dynamicdb01")
    public DataSource db01DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(DataSourceConstants.DS_KEY_DB02)
    @ConfigurationProperties(prefix = "spring.datasource.dynamicdb02")
    public DataSource db02DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource(){
        Map<Object, Object> datasourceMap = new HashMap<>();
        datasourceMap.put(DataSourceConstants.DS_KEY_DB01, db01DataSource());
        datasourceMap.put(DataSourceConstants.DS_KEY_DB02, db02DataSource());
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(datasourceMap);
        dynamicDataSource.setDefaultTargetDataSource(db01DataSource());
        return dynamicDataSource;
    }

}
