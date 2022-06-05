package com.lcl.myspring.homework;

import com.lcl.myspring.homework.entity.User;
import com.lcl.myspring.homework.service.UserService;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import java.util.List;

@SpringBootTest
class SpringHomeworkApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userSerivce;


    @Autowired
    StringEncryptor encryptor;

    @Test
    public void getPass() {
        String password = encryptor.encrypt("@#Lcl198127");
        System.out.println(password);
    }

    @Test
    public void test() throws Exception {
        // 准备，清空user表
        userSerivce.deleteAllUsers();

        // 插入5个用户
        userSerivce.create("User1", 11);
        userSerivce.create("User2", 13);
        userSerivce.create("User3", 36);
        userSerivce.create("User4", 19);
        userSerivce.create("User5", 18);

        // 查询名为User1的用户，判断年龄是否匹配
        List<User> userList = userSerivce.getByName("User1");
        Assert.assertEquals(11, userList.get(0).getAge().intValue());

        // 查数据库，应该有5个用户
        Assert.assertEquals(5, userSerivce.getAllUsers());

        // 删除两个用户
        userSerivce.deleteByName("User4");
        userSerivce.deleteByName("User5");

        // 查数据库，应该有3个用户
        Assert.assertEquals(3, userSerivce.getAllUsers());

    }

}
