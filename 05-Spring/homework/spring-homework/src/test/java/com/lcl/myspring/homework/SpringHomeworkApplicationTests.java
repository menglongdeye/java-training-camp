package com.lcl.myspring.homework;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringHomeworkApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    StringEncryptor encryptor;

    @Test
    public void getPass() {
        String password = encryptor.encrypt("@#Lcl198127");
        System.out.println(password);
    }

}
