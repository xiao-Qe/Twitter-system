package com.twittersystem.bean;

import org.springframework.stereotype.Component;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/24 14:43
 */
//常量类   常量设置在里面
@Component
public class Constant {
    //盐值
    public static final String PASSWORD_BEFORE = "password";
    public static final String PASSWORD_AFTER = "end";

    //权限
    public static final Integer USER = 0;
    public static final Integer ROOT = 1;
}
