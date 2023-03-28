package com.twittersystem;

import com.twittersystem.mapper.UserMapper;
import com.twittersystem.module.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

@SpringBootTest
class TwitterSystemApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.selectTest());
    }

    @Test
    void testFunction(){
        Long l = Calendar.getInstance().getTimeInMillis();
        Integer i = l.intValue();
        System.out.println(l);
        System.out.println(i);
    }

    @Test
    void testInsertUser(){
        System.out.println(userMapper.insertUser(new User("xiaoQe2",new Long(Calendar.getInstance().getTimeInMillis()),"123456789")));
    }

}
