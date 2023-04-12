package com.twittersystem.utils;

import com.twittersystem.mapper.TwitterMapper;
import com.twittersystem.mapper.TwitterScoreMapper;
import com.twittersystem.mapper.UserMapper;
import com.twittersystem.mapper.UserRecommendedMapper;
import com.twittersystem.module.system.TwitterSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/10 15:25
 */
@Component
public class ItemSimilarityUtil {

    private static TwitterScoreMapper twitterScoreMapper;
    private static UserRecommendedMapper userRecommendedMapper;
    private static UserMapper userMapper;



    @Autowired
    public ItemSimilarityUtil(TwitterScoreMapper twitterScoreMapper,
                              UserRecommendedMapper userRecommendedMapper,
                              UserMapper userMapper){
        ItemSimilarityUtil.twitterScoreMapper = twitterScoreMapper;
        ItemSimilarityUtil.userRecommendedMapper = userRecommendedMapper;
        ItemSimilarityUtil.userMapper = userMapper;
    }



    //评分相似的列表
    private static int[][] comMatrix;
    //除本人外喜欢人数
    private static int[] N;

    private static void setComMatrix(int[][] comMatrix){
        ItemSimilarityUtil.comMatrix = comMatrix;
    }
    private static void setN(int[] N){
        ItemSimilarityUtil.N = N;
    }

    //预处理列表
    private static TreeSet<TwitterSimilarity> preList = new TreeSet<TwitterSimilarity>(new Comparator<TwitterSimilarity>() {
        @Override
        public int compare(TwitterSimilarity o1, TwitterSimilarity o2) {
            if(!o1.getW().equals(o2.getW()))
                return (int) (o1.getW()-o2.getW())*100;
            else
                return o1.getLike()-o2.getLike();
        }
    });

    /**
     * @description: 获得推荐的二维数组即共线数组
     * @author xiaoQe
     * @date 2023/4/11 15:33
     * @version 1.0
     */
    private static void initializationComMatrix(Long userId){
        //获取所需要的数据
        //所有用户的ID
        List<Long> userIds = userMapper.selectAllUser();
        //所有的文章
        ArrayList<TwitterSimilarity> twitters = twitterScoreMapper.selectAllTwitter();
        //用户喜欢列表
        ArrayList<TwitterSimilarity> likeList;

        //定义当前矩阵   即用户喜欢矩阵 +5防止溢出
        int[][] curMatrix = new int[twitters.size()+5][twitters.size() + 5];
        //定义共现矩阵
        int[][] comMatrix = new int[twitters.size()+5][twitters.size() + 5];
        //喜欢文章人数（除本人）
        int[] N = new int[twitters.size()+5];

        //计算共现矩阵，获取除本人外的单个矩阵累加得到共现矩阵
        for(Long otherId : userIds){
            //是本人跳过
            if(userId.equals(otherId)) continue;
            //获取这个用户的喜欢列表
            likeList = userRecommendedMapper.selectLikeTwitterByUserId(otherId);

            //清空个人矩阵
            for (int i = 0;i < twitters.size();i++)
                for (int j = 0;j < twitters.size();j++)
                    curMatrix[i][j] = 0;

            //获得当前人的个人矩阵
            for (int i = 0;i < likeList.size();i++){
                int Tid1 = likeList.get(i).getId();
                ++N[Tid1];
                for (int j = i + 1;j < likeList.size();j++){
                    int Tid2 = likeList.get(j).getId();
                    ++curMatrix[Tid1][Tid2];
                    ++curMatrix[Tid2][Tid1];
                }
            }

            //累加得到共现矩阵
            for (int i = 0;i < twitters.size();i++){
                for (int j = 0;j < twitters.size();j++){
                    int Tid1 = twitters.get(i).getId();
                    int Tid2 = twitters.get(j).getId();
                    comMatrix[Tid1][Tid2] += curMatrix[Tid1][Tid2];
                    comMatrix[Tid1][Tid2] += curMatrix[Tid2][Tid1];
                }
            }
        }

        //给共现矩阵和喜欢数赋值
        setComMatrix(comMatrix);
        setN(N);
    }

    /**
     * @description: 获得推荐结果
     * @author xiaoQe
     * @date 2023/4/11 15:33
     * @version 1.0
     */
    public static ArrayList<Long> ItemRecommend(Long userId,Integer recommendNum){
        //清除所有元素做新的推荐
        preList.clear();
        //判断是否是第一次，需不需要初始化
        if((comMatrix==null||comMatrix.length==0)||(comMatrix.length==1&&comMatrix[0].length==0)){
            initializationComMatrix(userId);
        }
        //用户喜欢列表
        ArrayList<TwitterSimilarity> likeList = userRecommendedMapper.selectLikeTwitterByUserId(userId);
        //所有文章列表
        ArrayList<TwitterSimilarity> twitters = userRecommendedMapper.selectTwitterNotRecommended(userId);
        //判重数组
        boolean[] used = new boolean[comMatrix.length];
        for(TwitterSimilarity similarity : likeList){
            int Nij = 0;//同时喜欢ij的人数
            double Wij;//相似度
            TwitterSimilarity tmp;//当前文章

            int i = similarity.getId();
            for(TwitterSimilarity twitter : twitters){
                if(similarity.getId().equals(twitter.getId())) continue;
                int j = twitter.getId();

                Nij = comMatrix[i][j];
                Wij = (double) Nij/Math.sqrt(N[i] * N[j]);

                tmp = twitterScoreMapper.selectById(twitter.getId());
                tmp.setW(Wij);
                if(used[tmp.getId()]) continue;
                preList.add(tmp);
                used[tmp.getId()] = true;
            }
        }

        ArrayList<Long> recomLists = new ArrayList<>();
        for(int i = 0;preList.size() > 0 && i < recommendNum;i++){
            recomLists.add(preList.pollLast().getTwitterId());
            preList.pollLast();
        }
        if(recomLists.size() < recommendNum){
            //当推荐列表小于要推荐数量时 补充得分最高的文章
            List<Long> twitterIds = twitterScoreMapper.selectTwitterIdByScore(userId,recommendNum - recomLists.size());
            recomLists.addAll(twitterIds);
        }
        return recomLists;
    }


}
