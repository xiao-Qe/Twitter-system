package com.twittersystem.service.impl;

import com.twittersystem.bean.Constant;
import com.twittersystem.mapper.TwitterMapper;
import com.twittersystem.mapper.UndercarriageCauseMapper;
import com.twittersystem.mapper.UserMapper;
import com.twittersystem.module.User;
import com.twittersystem.module.twitter.InsertTwitter;
import com.twittersystem.module.twitter.TwitterCard;
import com.twittersystem.module.twitter.UpdateTwitter;
import com.twittersystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/11 14:37
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TwitterMapper twitterMapper;

    @Autowired
    private UndercarriageCauseMapper undercarriageCauseMapper;

    @Override
    public Boolean addUser(String userName, String password) {
        Long userId = Calendar.getInstance().getTimeInMillis();
        String newPassword = Constant.PASSWORD_BEFORE + password + Constant.PASSWORD_AFTER;
        User user = new User(userName, userId, newPassword,Constant.USER);
        Integer accountRole =  userMapper.insertUser(user);
        return accountRole != 0;
    }

    @Override
    public List<TwitterCard> getLikeList(Long userId) {
        List<TwitterCard> twitterCards = twitterMapper.selectLikeList(userId);
        if(twitterCards.isEmpty()){
            return null;
        }
        return twitterCards;
    }

    @Override
    public List<TwitterCard> getCollectList(Long userId) {
        List<TwitterCard> twitterCards = twitterMapper.selectCollectList(userId);
        if(twitterCards.isEmpty()){
            return null;
        }
        return twitterCards;
    }

    @Override
    public List<TwitterCard> getMyselfList(Long userId) {
        List<TwitterCard> twitterCards = twitterMapper.selectMyselfList(userId);
        if(twitterCards.isEmpty()){
            return null;
        }
        return twitterCards;
    }

    @Override
    public UpdateTwitter getUpdateTwitterByTwitterId(Long twitterId) {
        UpdateTwitter updateTwitter = twitterMapper.selectUpdateTwitterByTwitterId(twitterId);
        if(updateTwitter == null){
            return null;
        }
        return updateTwitter;
    }

    @Override
    public Boolean setTwitter(InsertTwitter insertTwitter) {
        insertTwitter.setState(Constant.TWITTER_STATE_AUDIT);
        Integer integer = twitterMapper.updateTwitter(insertTwitter);
        return integer != 0;
    }

    @Override
    public String getCause(Long twitterId) {
        String cause = undercarriageCauseMapper.selectCause(twitterId);
        return cause;
    }
}
