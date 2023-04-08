package com.twittersystem.service.impl;

import com.twittersystem.bean.Constant;
import com.twittersystem.mapper.TwitterMapper;
import com.twittersystem.mapper.UndercarriageCauseMapper;
import com.twittersystem.mapper.UserMapper;
import com.twittersystem.module.UndercarriageCause;
import com.twittersystem.module.User;
import com.twittersystem.module.twitter.AdministratorShowTwitter;
import com.twittersystem.module.twitter.AuditTwitter;
import com.twittersystem.service.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/5 17:48
 */
@Service
public class AdministratorServiceImpl implements IAdministratorService {

    @Autowired
    private TwitterMapper twitterMapper;

    @Autowired
    private UndercarriageCauseMapper undercarriageCauseMapper;

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

    @Override
    public AdministratorShowTwitter getAdministratorShowTwitterByTwitterId(Long twitterId,Long userId) {
        if (!checkPower(userId)){
            return null;
        }
        return twitterMapper.selectAdministratorShowTwitter(twitterId);
    }

    @Override
    public Boolean setTwitterStatePass(Long twitterId, Long userId) {
        if (!checkPower(userId)){
            return null;
        }
        int a = twitterMapper.updateState(twitterId,Constant.TWITTER_STATE_PASS);
        return a != 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean setTwitterStateUndercarriage(Long twitterId, Long userId,String cause) {
        if (!checkPower(userId)){
            return null;
        }
        try {
            int a = twitterMapper.updateState(twitterId,Constant.TWITTER_STATE_UNDERCARRIAGE);
            UndercarriageCause undercarriageCause = undercarriageCauseMapper.selectUndercarriageCause(twitterId);
            if(undercarriageCause == null){
                undercarriageCauseMapper.insetUndercarriageCause(twitterId,cause);
            }else {
                undercarriageCauseMapper.updateUndercarriageCause(twitterId,cause);
            }
            return a != 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getUserList(Long userId) {
        if (!checkPower(userId)){
            return null;
        }
        return userMapper.selectUserList();
    }

    @Override
    public Boolean deleteUser(Long userId, Long administratorId) {
        if(!checkPower(administratorId)){
            return null;
        }
        int integer = userMapper.deleteUser(userId);
        return integer != 0 ;
    }

    @Override
    public Boolean setUser(Long userId,User user) {
        if(!checkPower(userId)){
            return null;
        }
        user.setPassword(Constant.PASSWORD_BEFORE + user.getPassword() + Constant.PASSWORD_AFTER);
        int integer = userMapper.updateUser(user);
        return integer != 0 ;
    }
}
