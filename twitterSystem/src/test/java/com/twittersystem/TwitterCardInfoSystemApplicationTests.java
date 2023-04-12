package com.twittersystem;

import com.twittersystem.mapper.*;
import com.twittersystem.module.twitter.TwitterScore;
import com.twittersystem.utils.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @Autowired
    UndercarriageCauseMapper undercarriageCauseMapper;


    @Test
    void testUtils(){
//        ItemSimilarityUtil.test();
//        System.out.println(ItemSimilarityUtil.ItemRecommend(1679982052952L,5));
//        System.out.println(UserSimilarityUtil.recommend(1679982052952L,5));
        List<Long> recommend = new ArrayList<>();
        recommend.add(3361194964105L);
        recommend.add(3361200667203L);
        recommend.add(3361203572952L);
        recommend.add(3361203793223L);
        recommend.add(3361204090470L);
        System.out.println(twitterMapper.selectRecommendTwitter(recommend));
    }
    @Test
    void testUndercarriage(){
//        System.out.println(userMapper.selectUserList());
//        System.out.println(twitterMapper.selectUpdateTwitterByTwitterId(3360234663431L));
//        System.out.println(twitterScoreMapper.selectById(2));
        System.out.println(userRecommendedMapper.selectUserGrade(1679982052952L));
    }

    @Test
    void testTwitter(){
        System.out.println(twitterMapper.selectAuditTwitterList());
    }

    @Test
    void testUserRecommended(){
//        System.out.println(userRecommendedMapper.selectRecommend(1679982052952L,3360166928381L));
//        System.out.println(userRecommendedMapper.updateRecommended(new Recommended(3360166928381L,1679982052952L,1,1,0,1,4)));
//        System.out.println(userRecommendedMapper.selectLikeTwitterByUserId(1679982052952L));
        System.out.println(twitterScoreMapper.selectTwitterIdByScore(1679982052952L,3));
    }

    @Test
    void count(){
        System.out.println(TwitterScoreUtil.getTwitterScore(new TwitterScore(3360166928381L, 3, 10, 3, 30)));
    }

    @Test
    void testTwitterScore(){
//        System.out.println(twitterScoreMapper.selectTwitterScore(3360166928381L));
//        TwitterScore twitterScore = new TwitterScore(3360166928381L, null, 10, 3, 30);
//        System.out.println(twitterScoreMapper.updateTwitterScore(twitterScore));
        System.out.println(twitterScoreMapper.selectAllTwitter());
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
//        System.out.println(userMapper.selectFromUserId(1680595759132L));
        System.out.println(userMapper.selectAllUser());
    }

    @Test
    void testJWT(){
//        System.out.println(JWTUtil.creatToken(new Long(1679981470435l)));
        System.out.println(JWTUtil.checkToken("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiIxNjc5OTgxNDcwNDM1IiwiaWF0IjoxNjgwMDcyNDg2LCJleHAiOjE2ODAwNzI0OTB9.gGZjgpWjGCMkszu7M0YbKsOUfC8qWfDpv94o7CyQWM9pq2mDsKzWRRhy-od9IxjEI9YGm2lZ2SVwlltql5OBvg"));
//        System.out.println(JWTUtil.getUserIdFromToken("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiIxNjc5OTgxNDcwNDM1IiwiaWF0IjoxNjgwMDcyMjcyLCJleHAiOjE2ODAwNzU4NzJ9.e2qeGti1mGkCiJWnj4QVj9MSg43xY1fsl5E29wH8jYHE4N7W97-L4Q6vTJ-BY_CeLgz5Vjb1Ye7E1_SwzDpRSg"));
    }

}
