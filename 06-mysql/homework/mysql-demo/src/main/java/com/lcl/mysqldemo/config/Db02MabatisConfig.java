//package com.lcl.mysqldemo.config;
//
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "com.lcl.mysqldemo.mapper.db02",sqlSessionFactoryRef = "db02SqlSessionFactory")
//public class Db02MabatisConfig {
//
//    @Bean("db02SqlSessionFactory")
//    public SqlSessionFactory db02SqlSessionFactory(@Qualifier("db02") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
//
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        String locationPattern = "classpath*:/mapper/db02/*.xml";
//        mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources(locationPattern));
//        String typeAliasesPackage = "com.lcl.mysqldemo.entity.db02";
//        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
//        return mybatisSqlSessionFactoryBean.getObject();
//    }
//}
