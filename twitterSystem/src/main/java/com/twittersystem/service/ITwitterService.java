package com.twittersystem.service;

import com.twittersystem.module.InsertTwitter;
import com.twittersystem.module.TwitterDisplay;
import com.twittersystem.module.TwitterInfo;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/30 14:40
 */
public interface ITwitterService {

    /**
     * @description: 添加文章twitter
     * @author xiaoQe
     * @date 2023/3/30 17:17
     * @version 1.0
     */
    Boolean addTwitter(InsertTwitter insertTwitter);

    /**
     * @description: 获取文章
     * @author xiaoQe
     * @date 2023/4/1 16:21
     * @version 1.0
     */
    TwitterDisplay getTwitterDisplay(Long twitterId);
}
