package com.twittersystem.service;

import com.twittersystem.module.recommended.Recommended;
import com.twittersystem.module.twitter.InsertTwitter;
import com.twittersystem.module.twitter.TwitterCard;
import com.twittersystem.module.twitter.TwitterDisplay;
import com.twittersystem.module.twitter.TwitterScore;

import java.util.List;

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
    TwitterDisplay getTwitterDisplay(Long twitterId, Long userId);

    /**
     * @description: 获取文章展示集合
     * @author xiaoQe
     * @date 2023/4/1 17:29
     * @version 1.0
     */
    List<TwitterCard> getTwitterCardList();

    /**
     * @description: 修改文章评分方法
     * @author xiaoQe
     * @date 2023/4/2 17:27
     * @version 1.0
     */
    Integer setTwitterScore(TwitterScore twitterScore);

    /**
     * @description: 获取文章评分方法
     * @author xiaoQe
     * @date 2023/4/2 17:33
     * @version 1.0
     */
    TwitterScore getTwitterScore(Long twitterId);

    /**
     * @description: 点击喜欢修改文章评分和用户评分方法
     * @author xiaoQe
     * @date 2023/4/3 17:24
     * @version 1.0
     */
    Boolean setUserRecommendedAndTwitterScoreByRecommended(Recommended userRecommended);

    /**
     * @description: 获取分类文章方法
     * @author xiaoQe
     * @date 2023/4/22 16:08
     * @version 1.0
     */
    List<TwitterCard> getTwitterListByClassifyId(Integer classifyId);
}
