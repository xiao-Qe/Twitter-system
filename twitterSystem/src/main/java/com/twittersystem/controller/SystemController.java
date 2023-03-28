package com.twittersystem.controller;

import com.twittersystem.bean.ResBean;
import com.twittersystem.module.User;
import com.twittersystem.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/28 15:33
 */
@RequestMapping("/system")
@RestController
public class SystemController {
    @Autowired
    private IUserService userService;

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public ResBean registerUser(@Valid @RequestBody User user){
        Boolean addSuccess = userService.addUser(user.getUserName(), user.getPassword());
        if(addSuccess){
            return ResBean.ok("ok");
        }else {
            return ResBean.badRequest("添加出错");
        }
    }


}
