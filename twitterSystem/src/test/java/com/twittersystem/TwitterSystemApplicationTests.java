package com.twittersystem;

import com.twittersystem.mapper.UserMapper;
import com.twittersystem.module.User;
import com.twittersystem.utils.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

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
//        System.out.println(userMapper.insertUser(new User("xiaoQe2",new Long(Calendar.getInstance().getTimeInMillis()),"123456789")));
        System.out.println(new Date());
    }

    @Test
    void testJWT(){
//        System.out.println(JWTUtil.creatToken(new Long(1679981470435l)));
        System.out.println(JWTUtil.checkToken("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiIxNjc5OTgxNDcwNDM1IiwiaWF0IjoxNjgwMDcyNDg2LCJleHAiOjE2ODAwNzI0OTB9.gGZjgpWjGCMkszu7M0YbKsOUfC8qWfDpv94o7CyQWM9pq2mDsKzWRRhy-od9IxjEI9YGm2lZ2SVwlltql5OBvg"));
//        System.out.println(JWTUtil.getUserIdFromToken("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiIxNjc5OTgxNDcwNDM1IiwiaWF0IjoxNjgwMDcyMjcyLCJleHAiOjE2ODAwNzU4NzJ9.e2qeGti1mGkCiJWnj4QVj9MSg43xY1fsl5E29wH8jYHE4N7W97-L4Q6vTJ-BY_CeLgz5Vjb1Ye7E1_SwzDpRSg"));
    }

}
