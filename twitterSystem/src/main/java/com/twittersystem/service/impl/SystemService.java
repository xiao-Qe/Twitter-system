package com.twittersystem.service.impl;

import com.twittersystem.bean.Constant;
import com.twittersystem.mapper.UserMapper;
import com.twittersystem.module.User;
import com.twittersystem.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/28 16:00
 */
@Service
public class SystemService implements ISystemService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean registerUser(String userName, String password) {
        Long userId = Calendar.getInstance().getTimeInMillis();
        String newPassword = Constant.PASSWORD_BEFORE + password + Constant.PASSWORD_AFTER;
        User user = new User(userName, userId, newPassword,Constant.USER);
        Integer accountRole =  userMapper.insertUser(user);
        return accountRole != 0;
    }
}
