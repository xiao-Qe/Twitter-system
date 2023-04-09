package com.twittersystem.service;

import com.twittersystem.module.twitter.InsertTwitter;
import com.twittersystem.module.twitter.TwitterCard;
import com.twittersystem.module.twitter.UpdateTwitter;

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

    /**
     * @description: 返回用户作品列表
     * @author xiaoQe
     * @date 2023/4/8 18:21
     * @version 1.0
     */
    List<TwitterCard> getMyselfList(Long userId);

    /**
     * @description: 返回修改类
     * @author xiaoQe
     * @date 2023/4/9 14:21
     * @version 1.0
     */
    UpdateTwitter getUpdateTwitterByTwitterId(Long twitterId);

    /**
     * @description: 用户修改文章
     * @author xiaoQe
     * @date 2023/4/9 15:13
     * @version 1.0
     */
    Boolean setTwitter(InsertTwitter insertTwitter);

    /**
     * @description: 获取未通过原因
     * @author xiaoQe
     * @date 2023/4/9 15:51
     * @version 1.0
     */
    String getCause(Long twitterId);
}
