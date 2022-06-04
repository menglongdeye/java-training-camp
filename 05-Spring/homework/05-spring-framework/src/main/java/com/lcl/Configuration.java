package com.lcl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

@Data
@org.springframework.context.annotation.Configuration
public class Configuration {

    private String id;
    private String name ;

    @Bean(name = "mystudent3-name")
    @Qualifier(value = "primary")
    public Student3 testBean(){
        Student3 student3 = new Student3();
        student3.setId("demo");
        student3.setName("demoname");
        return student3;
    }

//    @Bean(value = "mystudent3-value")
//    public Student3 testBean2(){
//        return new Student3();
//    }
}
