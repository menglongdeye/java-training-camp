package com.lcl;

import com.lcl.demo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) applicationContext.getBean("student123");
        System.out.println("student ==== " + student);
        Student2 student2 = applicationContext.getBean(Student2.class);
        System.out.println("student2 ==== " + student2);
        Student3 order = applicationContext.getBean(Student3.class);
        System.out.println("order ==== " + order);
    }
}
