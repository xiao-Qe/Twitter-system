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

    //文章类型
    public static final Integer TWITTER_TYPE_PRIVATE = 0;   //私密
    public static final Integer TWITTER_TYPE_PUBLIC = 1;    //公开

    //文章状态
    public static final Integer TWITTER_STATE_AUDIT = 0;  //审核
    public static final Integer TWITTER_STATE_PASS = 1;  //通过
    public static final Integer TWITTER_STATE_UNDERCARRIAGE = 2;  //下架

    //用户评分表枚举
    public static final Integer IS_VIEW = 1;
    public static final Integer NOT_VIEW = 0;
    public static final Integer IS_LIKE = 1;
    public static final Integer NOT_LIKE = 0;
    public static final Integer IS_COLLECT = 1;
    public static final Integer NOT_COLLECT = 0;
}
