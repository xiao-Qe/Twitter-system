package com.twittersystem.service.impl;

import com.twittersystem.bean.Constant;
import com.twittersystem.mapper.TwitterMapper;
import com.twittersystem.mapper.UserMapper;
import com.twittersystem.module.User;
import com.twittersystem.module.twitter.AuditTwitter;
import com.twittersystem.service.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/5 17:48
 */
@Service
public class IAdministratorServiceImpl implements IAdministratorService {

    @Autowired
    private TwitterMapper twitterMapper;

    @Autowired
    private UserMapper userMapper;

    private Boolean checkPower(Long userId){
        User user = userMapper.selectFromUserId(userId);
        if(user.getPower().equals(Constant.ROOT)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<AuditTwitter> getAuditTwitterList(Long userId) {
        if(!checkPower(userId)){
            return null;
        }
        return twitterMapper.selectAuditTwitterList();
    }
}
