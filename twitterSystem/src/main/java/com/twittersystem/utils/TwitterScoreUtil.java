package com.twittersystem.utils;

import com.twittersystem.module.TwitterScore;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/2 16:38
 */
@Component
public class TwitterScoreUtil {
    //少量时的评分占比
    private static final Integer FEW_LIKE_SCORE = 2;
    private static final Integer FEW_COLLECT_SCORE = 1;

    //足量时的评分占比
    private static final Integer LIKE_SCORE = 1;
    private static final Integer COLLECT_SCORE = 2;

    /**
     * @description: int类型相除保留两位小数方法
     * @author xiaoQe
     * @date 2023/4/2 16:44
     * @version 1.0
     */
    private static Integer integerCount(Integer num,Integer view){
        DecimalFormat df=new DecimalFormat("0.00");
        double v = Double.parseDouble(df.format(num / (float) view));
        return (int) (v * 100);
    }


    /**
     * @description: 获取文章评分方法
     * @author xiaoQe
     * @date 2023/4/2 16:57
     * @version 1.0
     */
    public static TwitterScore getTwitterScore(TwitterScore twitterScore){
        Integer score;
        if(twitterScore.getView() <= 50){
            score = 300 - ((twitterScore.getView() - twitterScore.getLike()) * FEW_LIKE_SCORE
                    + (twitterScore.getView() - twitterScore.getCollect()) * FEW_COLLECT_SCORE);
        }else {
            score = integerCount(twitterScore.getLike(), twitterScore.getView()) * LIKE_SCORE
                    + integerCount(twitterScore.getCollect(), twitterScore.getView()) * COLLECT_SCORE;
        }
        twitterScore.setScore(score);
        return twitterScore;
    }
}
