package com.twittersystem.service;

import com.twittersystem.module.User;
import org.springframework.stereotype.Service;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/11 14:36
 */

public interface IUserService {
    public Integer addUser(User user);
}
