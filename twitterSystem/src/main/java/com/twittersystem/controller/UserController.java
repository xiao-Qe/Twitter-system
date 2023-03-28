package com.twittersystem.controller;

import com.twittersystem.bean.ResBean;
import com.twittersystem.module.User;
import com.twittersystem.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/11 14:18
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("添加用户")
    @PostMapping("/add_user")
    public ResBean addUser(@Valid @RequestBody User user){
        Boolean addSuccess = userService.addUser(user.getUserName(), user.getPassword());
        if(addSuccess){
            return ResBean.ok("ok");
        }else {
            return ResBean.badRequest("添加出错");
        }
    }
}
