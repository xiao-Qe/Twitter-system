package com.twittersystem.service.impl;

import com.twittersystem.bean.Constant;
import com.twittersystem.mapper.UserMapper;
import com.twittersystem.module.User;
import com.twittersystem.service.ISystemService;
import com.twittersystem.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/28 16:00
 */
@Service
public class SystemServiceImpl implements ISystemService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String registerUser(Long userId,String userName, String password) {
        String newPassword = Constant.PASSWORD_BEFORE + password + Constant.PASSWORD_AFTER;
        User user = new User(userName, userId, newPassword,Constant.USER);
        Integer accountRole =  userMapper.insertUser(user);
        if(accountRole == 0){
            return null;
        }
        return JWTUtil.creatToken(userId);
    }

    @Override
    public String login(Long userId, String password) {
        String newPassword = Constant.PASSWORD_BEFORE + password + Constant.PASSWORD_AFTER;
        Long id = userMapper.selectUser(userId,newPassword);
        if(id == null || id.equals(0L) ){
            return null;
        }
        return JWTUtil.creatToken(id);

    }

    @Override
    public User getUserByUserId(Long userId) {
        User user = userMapper.selectFromUserId(userId);
        if(user == null){
            return null;
        }
        return user;
    }
}
