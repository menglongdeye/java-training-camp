package com.lcl.spring;

import com.lcl.spring.config.WebInfo;
import com.lcl.spring.service.Student;
import com.lcl.spring.utils.SpringBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MySpringApplication.class, args);
        SpringBeanUtils.setApplicationContext(applicationContext);
//        Student student = applicationContext.getBean(Student.class);
//        System.out.printf("student" + "-" + student.toString());
        WebInfo webInfo = applicationContext.getBean(WebInfo.class);
        System.out.printf("weninfo====" + webInfo.getName());
    }


}
