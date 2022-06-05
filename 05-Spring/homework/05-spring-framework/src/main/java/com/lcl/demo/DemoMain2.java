package com.lcl.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoMain2 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Student student123 = context.getBean(Student.class);

        Student student123 = (Student) context.getBean("student123");
        System.out.println(student123.toString());

        student123.print();

        Student student100 = (Student) context.getBean("student100");
        System.out.println(student100.toString());

        student100.print();


        Klass class1 = context.getBean(Klass.class);
        System.out.println(class1);
        System.out.println("Klass对象AOP代理后的实际类型："+class1.getClass());
        System.out.println("Klass对象AOP代理后的实际类型是否是Klass子类："+(class1 instanceof Klass));

        class1.dong();
        System.out.println("   *****====> class1.dong() " + (System.currentTimeMillis()) + " ms");

        ISchool school = context.getBean(ISchool.class);
        System.out.println(school);
        System.out.println("ISchool接口的对象AOP代理后的实际类型："+school.getClass());

    }
}
