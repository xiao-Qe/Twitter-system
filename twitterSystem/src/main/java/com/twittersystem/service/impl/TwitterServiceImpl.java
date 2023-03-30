package com.twittersystem.service.impl;

import com.twittersystem.mapper.TwitterMapper;
import com.twittersystem.mapper.TwitterScoreMapper;
import com.twittersystem.module.InsertTwitter;
import com.twittersystem.service.ITwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

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


}
