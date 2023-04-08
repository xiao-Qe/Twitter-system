package com.twittersystem.service;

import com.twittersystem.module.twitter.TwitterCard;

import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/11 14:36
 */

public interface IUserService {
    Boolean addUser(String userName, String password);

    /**
     * @description: 用户获取喜欢列表
     * @author xiaoQe
     * @date 2023/4/8 17:32
     * @version 1.0
     */
    List<TwitterCard> getLikeList(Long userId);

    /**
     * @description: 用户获取收藏列表
     * @author xiaoQe
     * @date 2023/4/8 17:34
     * @version 1.0
     */
    List<TwitterCard> getCollectList(Long userId);
}
