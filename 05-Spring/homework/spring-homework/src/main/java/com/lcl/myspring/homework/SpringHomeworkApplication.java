package com.lcl.myspring.homework;

import com.lcl.myspring.homework.utils.SpringBeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringHomeworkApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringHomeworkApplication.class, args);
        SpringBeanUtils.setApplicationContext(run);
    }

}
