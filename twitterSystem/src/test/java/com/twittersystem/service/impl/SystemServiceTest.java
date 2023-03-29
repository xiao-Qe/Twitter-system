package com.twittersystem.service.impl;

import com.twittersystem.service.ISystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/29 15:06
 */
@SpringBootTest
class SystemServiceTest {

    @Autowired
    private ISystemService systemService;

    @Test
    void registerUser() {
    }

    @Test
    void login() {
        String login = systemService.login(new Long(167981470435L), "123456789");
        System.out.println(login);
    }
}