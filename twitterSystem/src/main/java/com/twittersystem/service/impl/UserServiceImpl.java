package com.twittersystem.service.impl;

import com.twittersystem.mapper.UserMapper;
import com.twittersystem.module.User;
import com.twittersystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/11 14:37
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer addUser(User user) {
        return null;
    }
}
