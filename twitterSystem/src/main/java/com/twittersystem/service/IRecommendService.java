package com.twittersystem.service;

import com.twittersystem.module.twitter.TwitterCard;

import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/12 15:13
 */
public interface IRecommendService {

    /**
     * @description: 推荐方法
     * @author xiaoQe
     * @date 2023/4/12 16:58
     * @version 1.0
     */
    List<TwitterCard> recommendTwitter(Long userId);
}
