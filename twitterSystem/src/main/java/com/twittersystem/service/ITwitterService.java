package com.twittersystem.service;

import com.twittersystem.module.InsertTwitter;

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
}
