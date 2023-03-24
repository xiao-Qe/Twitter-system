package com.twittersystem.controller;

import com.twittersystem.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/11 14:18
 */
@Controller("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @ApiOperation("添加用户")
    @PostMapping("/add_user")
    public void addUser(){

    }
}
