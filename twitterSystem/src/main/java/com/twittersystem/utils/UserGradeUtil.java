package com.twittersystem.utils;

import com.twittersystem.module.recommended.Grade;
import com.twittersystem.module.recommended.Recommended;
import org.springframework.stereotype.Component;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/3 16:35
 * 用户评分工具类
 */
@Component
public class UserGradeUtil {
    //用户行为评分系数
    //观看评分
    private static final Integer VIEW_GRADE = 2;
    //喜欢评分
    private static final Integer LIKE_GRADE = 4;
    //收藏评分
    private static final Integer COLLECT_GRADE = 8;

    /**
     * @description: 用户评分方法
     * @author xiaoQe
     * @date 2023/4/3 16:37
     * @version 1.0
     */
    public static Recommended getUserGrade(Recommended userRecommended){
        Integer grade = 0;
        if(userRecommended.getLike()){
            grade += LIKE_GRADE;
        }
        if (userRecommended.getView()){
            grade += VIEW_GRADE;
        }
        if (userRecommended.getCollect()){
            grade += COLLECT_GRADE;
        }
        userRecommended.setGrade(grade);
        return userRecommended;
    }
}
