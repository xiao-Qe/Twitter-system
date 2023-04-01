package com.twittersystem.controller;

import com.twittersystem.bean.ResBean;
import com.twittersystem.module.User;
import com.twittersystem.service.ISystemService;
import com.twittersystem.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
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
        Long userId = Calendar.getInstance().getTimeInMillis();
        String token = systemService.registerUser(userId,user.getUserName(), user.getPassword());
        if(token == null || token.equals("")){
            return ResBean.badRequest("添加出错");
        }else {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("token",token);
            User userInfo = new User();
            userInfo.setUserName(user.getUserName());
            userInfo.setUserId(userId);
            hashMap.put("userInfo",userInfo);
            return ResBean.ok("ok",hashMap);
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
