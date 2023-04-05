package com.twittersystem.service.impl;

import com.twittersystem.mapper.TwitterMapper;
import com.twittersystem.mapper.TwitterScoreMapper;
import com.twittersystem.mapper.UserRecommendedMapper;
import com.twittersystem.module.recommended.Recommended;
import com.twittersystem.module.twitter.InsertTwitter;
import com.twittersystem.module.twitter.TwitterCard;
import com.twittersystem.module.twitter.TwitterDisplay;
import com.twittersystem.module.twitter.TwitterScore;
import com.twittersystem.service.ITwitterService;
import com.twittersystem.utils.TwitterScoreUtil;
import com.twittersystem.utils.UserGradeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/30 14:40
 */
@Service
public class TwitterServiceImpl implements ITwitterService {

    @Autowired
    private TwitterMapper twitterMapper;

    @Autowired
    private TwitterScoreMapper twitterScoreMapper;

    @Autowired
    private UserRecommendedMapper userRecommendedMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addTwitter(InsertTwitter insertTwitter) {
        try {
            Long creatTime = Calendar.getInstance().getTimeInMillis();
            twitterMapper.insertTwitter(insertTwitter,creatTime/1000);
            twitterScoreMapper.insertScore(insertTwitter.getId());
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TwitterDisplay getTwitterDisplay(Long twitterId, Long userId) {
        try {
            //修改文章评分
            TwitterScore twitterScore =twitterScoreMapper.selectTwitterScore(twitterId);
            twitterScore.setView(twitterScore.getView() + 1);
            TwitterScore newTwitterScore = TwitterScoreUtil.getTwitterScore(twitterScore);
            Integer integer = twitterScoreMapper.updateTwitterScore(newTwitterScore);

            //修改用户评分
            Recommended userRecommended = userRecommendedMapper.selectRecommend(userId, twitterId);
            if(userRecommended == null){
                Recommended newRecommended = new Recommended(twitterId,userId,false,false,false,false,0);
                newRecommended.setView(true);
                Recommended userGrade = UserGradeUtil.getUserGrade(newRecommended);
                userRecommendedMapper.insertUserRecommended(userGrade);
            }else {
                userRecommended.setView(true);
                Recommended userGrade = UserGradeUtil.getUserGrade(userRecommended);
                Integer integer1 = userRecommendedMapper.updateRecommended(userGrade);
            }

            //返回文章内容
            return twitterMapper.selectTwitterDisplay(twitterId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TwitterCard> getTwitterCardList() {
        return twitterMapper.selectTwitterCardList();
    }

    @Override
    public Integer setTwitterScore(TwitterScore twitterScore) {
        TwitterScore newTwitterScore = TwitterScoreUtil.getTwitterScore(twitterScore);
        return twitterScoreMapper.updateTwitterScore(newTwitterScore);
    }

    @Override
    public TwitterScore getTwitterScore(Long twitterId) {
        return twitterScoreMapper.selectTwitterScore(twitterId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean setUserRecommendedAndTwitterScoreByRecommended(Recommended userRecommended) {
        try {
            //修改twitter评分
            TwitterScore twitterScore = twitterScoreMapper.selectTwitterScore(userRecommended.getTwitterId());
            Recommended recommended = userRecommendedMapper.selectRecommend(userRecommended.getUserId(), userRecommended.getTwitterId());
            //判断like是否改变
            if(!recommended.getLike().equals(userRecommended.getLike())){
                if(userRecommended.getLike()){
                    twitterScore.setLike(twitterScore.getLike() + 1);
                }else {
                    twitterScore.setLike(twitterScore.getLike() - 1);
                }
            }
            //判断collect是否改变
            if(!recommended.getCollect().equals(userRecommended.getCollect())){
                if(userRecommended.getCollect()){
                    twitterScore.setCollect(twitterScore.getCollect() + 1);
                }else {
                    twitterScore.setCollect(twitterScore.getCollect() - 1);
                }
            }
            TwitterScore newTwitterScore = TwitterScoreUtil.getTwitterScore(twitterScore);
            twitterScoreMapper.updateTwitterScore(newTwitterScore);

            //修改用户评分
            Recommended userGrade = UserGradeUtil.getUserGrade(userRecommended);
            userRecommendedMapper.updateRecommended(userGrade);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
