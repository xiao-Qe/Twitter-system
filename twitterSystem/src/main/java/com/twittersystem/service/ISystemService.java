package com.twittersystem.service;

import com.twittersystem.module.User;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/28 16:00
 */
public interface ISystemService {
    /**
     * @description: 注册
     * @author xiaoQe
     * @date 2023/3/28 16:02
     * @version 1.0
     */
    public Boolean registerUser(String userName,String password);

    /**
     * @description: 用户登录生成token
     * @author xiaoQe
     * @date 2023/3/29 14:52
     * @version 1.0
     */
    public String login(Long userId,String password);

    /**
     * @description: 查询用户信息
     * @author xiaoQe
     * @date 2023/3/29 15:34
     * @version 1.0
     */
    public User getUserByUserId(Long userId);
}
