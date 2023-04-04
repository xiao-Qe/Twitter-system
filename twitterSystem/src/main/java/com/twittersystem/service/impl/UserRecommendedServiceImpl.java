package com.twittersystem.service.impl;

import com.twittersystem.mapper.UserRecommendedMapper;
import com.twittersystem.module.recommended.Recommended;
import com.twittersystem.service.IUserRecommendedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/3 17:47
 */
@Service
public class UserRecommendedServiceImpl implements IUserRecommendedService{
    @Autowired
    private UserRecommendedMapper userRecommendedMapper;


    @Override
    public Recommended getUserRecommended(Long userId, Long twitterId) {
        return userRecommendedMapper.selectRecommend(userId,twitterId);
    }

//    @Override
//    public Integer setUserRecommended(Recommended userRecommend) {
//        return userRecommendedMapper.updateRecommended(userRecommend);
//    }
}
