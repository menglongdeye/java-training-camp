//package com.lcl.boot;
//
//import com.lcl.boot.service.ISchool;
//import com.lcl.boot.service.Klass;
//import com.lcl.boot.service.Student;
//import com.lcl.boot.utils.SpringBeanUtils;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//@SpringBootApplication
//public class MySpringApplication {
//
//    public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(MySpringApplication.class, args);
//        SpringBeanUtils.setApplicationContext(applicationContext);
////        Student student = applicationContext.getBean(Student.class);
////        System.out.printf("student" + "-" + student.toString());
//        Student student = applicationContext.getBean(Student.class);
//        System.out.println("student====" + student.getId() +student.getName());
//        Klass klass = applicationContext.getBean(Klass.class);
//        System.out.println("klass====" + klass.getStudents());
//        klass.dong();
//        ISchool iSchool = applicationContext.getBean(ISchool.class);
//        iSchool.ding();
//    }
//
//
//}
