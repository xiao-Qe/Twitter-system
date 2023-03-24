package com.twittersystem;

import com.twittersystem.mapper.UserMapper;
import com.twittersystem.module.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TwitterSystemApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.selectTest());
    }
    @Test
    void testInsertUser(){
        System.out.println(userMapper.insertUser(new User("xiaoQe1",123457,"123456789")));
    }

}
