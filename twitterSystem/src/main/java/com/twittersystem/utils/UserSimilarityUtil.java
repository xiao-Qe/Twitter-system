package com.twittersystem.utils;

import com.twittersystem.mapper.UserMapper;
import com.twittersystem.mapper.UserRecommendedMapper;
import com.twittersystem.module.system.UserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/12 15:19
 */
@Component
public class UserSimilarityUtil {
    private static UserMapper userMapper;
    private static UserRecommendedMapper userRecommendedMapper;

    @Autowired
    public UserSimilarityUtil(UserMapper userMapper,
                              UserRecommendedMapper userRecommendedMapper){
        UserSimilarityUtil.userMapper = userMapper;
        UserSimilarityUtil.userRecommendedMapper = userRecommendedMapper;
    }

    /**
     * @description: 获得用户相邻矩阵
     * @author xiaoQe
     * @date 2023/4/12 15:27
     * @version 1.0
     */
    private static Map<Double,Long> computeNearestNeighbor(Long userId){
        Map<Double, Long> distances = new TreeMap<>();

        //获取所有用户
        List<Long> users = userMapper.selectAllUser();
        //用户喜欢列表
        List<UserScore> userScores = userRecommendedMapper.selectUserGrade(userId);
        for(Long otherUser : users){
            //如果是本人跳过
            if(userId.equals(otherUser)) continue;
            //其他人喜欢列表
            List<UserScore> otherScore = userRecommendedMapper.selectUserGrade(otherUser);
            Double distance = pearson_dis(userScores,otherScore);
            distances.put(distance,otherUser);
        }

        return distances;
    }

    /**
     * @description: 计算用户之间的距离
     * @author xiaoQe
     * @date 2023/4/12 15:28
     * @version 1.0
     */
    private static Double pearson_dis(List<UserScore> userScore,List<UserScore> otherScore){
        if (otherScore.size() == 0){
            return 0.0;
        }
        int n;
        if(otherScore.size() > userScore.size()){
            n = userScore.size();
        }else {
            n = otherScore.size();
        }
        //得到用户的评分列表
        List<Integer> rating1ScoreCollect = userScore.stream().map(UserScore::getGrade).collect(Collectors.toList());
        //得到其他的评分列表
        List<Integer> rating2ScoreCollect = otherScore.stream().map(UserScore::getGrade).collect(Collectors.toList());

        //得到用户评分累加值
        double Ex= rating1ScoreCollect.stream().mapToDouble(x->x).sum();
        //其他用户评分累加值
        double Ey= rating2ScoreCollect.stream().mapToDouble(y->y).sum();
        //累加值的平方
        double Ex2=rating1ScoreCollect.stream().mapToDouble(x->Math.pow(x,2)).sum();
        double Ey2=rating2ScoreCollect.stream().mapToDouble(y->Math.pow(y,2)).sum();
        //x*y累加值
        double Exy= IntStream.range(0,n).mapToDouble(i->rating1ScoreCollect.get(i)*rating2ScoreCollect.get(i)).sum();
        double numerator=Exy-Ex*Ey/n;
        double denominator=Math.sqrt((Ex2-Math.pow(Ex,2)/n)*(Ey2-Math.pow(Ey,2)/n));
        if (denominator==0) return 0.0;
        //相似系数
        return numerator/denominator;
    }

    /**
     * @description: 推荐方法
     * @author xiaoQe
     * @date 2023/4/12 15:53
     * @version 1.0
     */
    public static List<Long> recommend(Long userId,Integer recommendNum){
        Map<Double, Long> distances = computeNearestNeighbor(userId);
        Iterator<Long> iterator = distances.values().iterator();

        ArrayList<Long> recommendation = new ArrayList<>();
        while (recommendation.size() < recommendNum) {
            //最近邻居
            Long nearest = iterator.next();
            //用户看过（评价过的列表）
            List<UserScore> userScores = userRecommendedMapper.selectRecommendedTwitterByUserId(userId);
            //邻居看过的列表
            List<UserScore> nearestScores = userRecommendedMapper.selectUserGrade(nearest);

            //比较是否都看过进行排序
            //如果推荐过就跳过未推荐过就加入
            outer:
            for (UserScore otherScore : nearestScores) {
                for (UserScore userScore : userScores) {
                    //判断用户是否看过若看过则跳出此次循环
                    if (otherScore.getTwitterId().equals(userScore.getTwitterId())) continue outer;
                }
                if (recommendation.size() >= recommendNum) {
                    break;
                }
                recommendation.add(otherScore.getTwitterId());
            }
        }
        Collections.sort(recommendation);
        return recommendation;
    }
}
