package com.twittersystem;

import com.twittersystem.mapper.*;
import com.twittersystem.module.twitter.TwitterScore;
import com.twittersystem.utils.JWTUtil;
import com.twittersystem.utils.TwitterScoreUtil;
import com.twittersystem.utils.TwitterUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

@SpringBootTest
class TwitterCardInfoSystemApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ClassifyMapper classifyMapper;

    @Autowired
    TwitterScoreMapper twitterScoreMapper;

    @Autowired
    UserRecommendedMapper userRecommendedMapper;

    @Autowired
    TwitterMapper twitterMapper;

    @Test
    void testTwitter(){
        System.out.println(twitterMapper.selectAuditTwitterList());
    }

    @Test
    void testUserRecommended(){
//        System.out.println(userRecommendedMapper.selectRecommend(1679982052952L,3360166928381L));
//        System.out.println(userRecommendedMapper.updateRecommended(new Recommended(3360166928381L,1679982052952L,1,1,0,1,4)));
    }

    @Test
    void count(){
        System.out.println(TwitterScoreUtil.getTwitterScore(new TwitterScore(3360166928381L, 3, 10, 3, 30)));
    }

    @Test
    void testTwitterScore(){
//        System.out.println(twitterScoreMapper.selectTwitterScore(3360166928381L));
        TwitterScore twitterScore = new TwitterScore(3360166928381L, null, 10, 3, 30);
        System.out.println(twitterScoreMapper.updateTwitterScore(twitterScore));
    }

    @Test
    void contextLoads() {
        System.out.println(userMapper.selectTest());
    }

    @Test
    void testClassify(){
        System.out.println(classifyMapper.selectClassBySuperId(1000));
    }

    @Test
    void testTwitterIdUtil(){
        System.out.println(TwitterUtil.getTwitterId(1679992299416L));
    }

    @Test
    void testFunction(){
        Long l = Calendar.getInstance().getTimeInMillis();
        Integer i = l.intValue();
        System.out.println(l);
        System.out.println(i);
    }

    @Test
    void testInsertUser(){
//        System.out.println(userMapper.insertUser(new User("xiaoQe2",new Long(Calendar.getInstance().getTimeInMillis()),"123456789")));
//        System.out.println(new Date());
        System.out.println(userMapper.selectFromUserId(1680595759132L));
    }

    @Test
    void testJWT(){
//        System.out.println(JWTUtil.creatToken(new Long(1679981470435l)));
        System.out.println(JWTUtil.checkToken("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiIxNjc5OTgxNDcwNDM1IiwiaWF0IjoxNjgwMDcyNDg2LCJleHAiOjE2ODAwNzI0OTB9.gGZjgpWjGCMkszu7M0YbKsOUfC8qWfDpv94o7CyQWM9pq2mDsKzWRRhy-od9IxjEI9YGm2lZ2SVwlltql5OBvg"));
//        System.out.println(JWTUtil.getUserIdFromToken("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiIxNjc5OTgxNDcwNDM1IiwiaWF0IjoxNjgwMDcyMjcyLCJleHAiOjE2ODAwNzU4NzJ9.e2qeGti1mGkCiJWnj4QVj9MSg43xY1fsl5E29wH8jYHE4N7W97-L4Q6vTJ-BY_CeLgz5Vjb1Ye7E1_SwzDpRSg"));
    }

}
