package com.twittersystem.service.impl;

import com.twittersystem.mapper.TwitterMapper;
import com.twittersystem.mapper.UserRecommendedMapper;
import com.twittersystem.module.recommended.Recommended;
import com.twittersystem.module.twitter.TwitterCard;
import com.twittersystem.service.IRecommendService;
import com.twittersystem.utils.ItemSimilarityUtil;
import com.twittersystem.utils.UserSimilarityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/12 15:14
 * 推荐service
 */
@Service
public class RecommendServiceImpl implements IRecommendService {

    @Autowired
    private TwitterMapper twitterMapper;
    @Autowired
    private UserRecommendedMapper userRecommendedMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<TwitterCard> recommendTwitter(Long userId) {
        //推荐九个 获得九个的twitterId
        ArrayList<Long> longs = ItemSimilarityUtil.ItemRecommend(userId, 5);
        ArrayList<Long> recommend = UserSimilarityUtil.recommend(userId, 4);
        ArrayList<Long> userRecommend = new ArrayList<>();
        userRecommend.addAll(longs);
        userRecommend.addAll(recommend);
        //推荐的推荐Card
        List<TwitterCard> twitterCards = twitterMapper.selectRecommendTwitter(userRecommend);

        //查看是否在评分表中存在若存在则修改 不存在则添加
        for(Long twitterId : userRecommend){
            Recommended recommended = userRecommendedMapper.selectRecommend(userId, twitterId);
            if(recommended == null){
                Recommended userRecommended = new Recommended(twitterId,userId,true,false,false,false,0);
                userRecommendedMapper.insertUserRecommended(userRecommended);
            }else {
                recommended.setRecommend(true);
                userRecommendedMapper.updateRecommended(recommended);
            }
        }

        return twitterCards;
    }
}
