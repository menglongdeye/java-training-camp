package com.lcl.boot;

import com.lcl.boot.config.ConfigProperty;
import com.lcl.boot.service.ISchool;
import com.lcl.boot.service.Klass;
import com.lcl.boot.service.School;
import com.lcl.boot.service.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(ConfigProperty.class)
@ConditionalOnProperty(prefix = "lcl.boot", name = "enabled", havingValue = "true", matchIfMissing = false)
public class MyDemoAutoConfiguration {


    @Autowired
    ConfigProperty property;

    @Bean
    @ConditionalOnProperty(prefix = "lcl.boot", name = "id", matchIfMissing = true)
    public Student creatStudent(){
        return new Student(property.getId(), property.getName());
    }

    @Bean(value = "defaultBean")
    @ConditionalOnMissingBean(Student.class)
    public Student creatDefaultStudent(){
        return new Student(789, "test");
    }

    @Bean
    public Klass createKlass(Student student){
        Klass klass = new Klass();
        List<Student> list = new ArrayList<>();
        list.add(student);
        klass.setStudents(list);
        return klass;
    }

    @Bean
    public ISchool createISchool(Klass klass, Student student){
        ISchool iSchool = new School();
        return iSchool;
    }
}
