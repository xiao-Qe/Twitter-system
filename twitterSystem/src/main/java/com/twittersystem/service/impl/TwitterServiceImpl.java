package com.twittersystem.service.impl;

import com.twittersystem.mapper.TwitterMapper;
import com.twittersystem.mapper.TwitterScoreMapper;
import com.twittersystem.module.*;
import com.twittersystem.service.ITwitterService;
import com.twittersystem.utils.TwitterScoreUtil;
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
    public TwitterDisplay getTwitterDisplay(Long twitterId) {
        try {
            TwitterScore twitterScore =twitterScoreMapper.selectTwitterScore(twitterId);
            twitterScore.setView(twitterScore.getView() + 1);
            TwitterScore newTwitterScore = TwitterScoreUtil.getTwitterScore(twitterScore);
            Integer integer = twitterScoreMapper.updateTwitterScore(newTwitterScore);
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


}
