package com.twittersystem.service.impl;

import com.twittersystem.bean.Constant;
import com.twittersystem.mapper.TwitterMapper;
import com.twittersystem.mapper.UserMapper;
import com.twittersystem.module.User;
import com.twittersystem.module.twitter.TwitterCard;
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
}
