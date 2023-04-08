package com.twittersystem.service;

import com.twittersystem.module.recommended.Recommended;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/3 17:46
 */
public interface IUserRecommendedService {

    /**
     * @description: 获得用户评分表
     * @author xiaoQe
     * @date 2023/4/3 17:50
     * @version 1.0
     */
    Recommended getUserRecommended(Long userId,Long twitterId);

    /**
     * @description: 修改用户评分表
     * @author xiaoQe
     * @date 2023/4/3 20:52
     * @version 1.0
     */
//    Integer setUserRecommended(Recommended userRecommend);
}
