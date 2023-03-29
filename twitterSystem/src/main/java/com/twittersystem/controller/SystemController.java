package com.twittersystem.controller;

import com.twittersystem.bean.ResBean;
import com.twittersystem.module.User;
import com.twittersystem.service.ISystemService;
import com.twittersystem.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/28 15:33
 */
@RequestMapping("/system")
@RestController
public class SystemController {
    @Autowired
    private ISystemService systemService;

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public ResBean registerUser(@Valid @RequestBody User user){
        Boolean addSuccess = systemService.registerUser(user.getUserName(), user.getPassword());
        if(addSuccess){
            return ResBean.ok("ok");
        }else {
            return ResBean.badRequest("添加出错");
        }
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResBean login(@Valid @RequestBody User user){
        String token = systemService.login(user.getUserId(),user.getPassword());
        if(token == null || token.equals("")){
            return ResBean.unauthorized("验证失败");
        }
        User userInfo = systemService.getUserByUserId(user.getUserId());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("token",token);
        hashMap.put("userInfo",userInfo);
        return ResBean.ok("ok",hashMap);
    }
}
