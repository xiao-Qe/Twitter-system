package com.twittersystem.service.impl;

import com.twittersystem.service.ITwitterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/1 17:04
 */
@SpringBootTest
class TwitterServiceImplTest {
    @Autowired
    private ITwitterService twitterService;

    @Test
    void getTwitterDisplay() {
        System.out.println(twitterService.getTwitterDisplay(3360234813318L));
    }

    @Test
    void testGetTwitterCardList(){
        System.out.println(twitterService.getTwitterCardList());
    }
}