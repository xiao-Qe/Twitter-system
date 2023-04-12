package com.twittersystem.mapper;

import com.twittersystem.module.recommended.Recommended;
import com.twittersystem.module.system.TwitterSimilarity;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/2 16:05
 */
@Mapper
@Repository
public interface UserRecommendedMapper {
    /**
     * @description: 查询已推荐
     * @author xiaoQe
     * @date 2023/4/2 16:28
     * @version 1.0
     */
    @Select("select * from user_recommended where twitter_id = #{twitterId} and user_id = #{userId}")
    Recommended selectRecommend(Long userId, Long twitterId);

    /**
     * @description: 修改用户评分
     * @author xiaoQe
     * @date 2023/4/3 17:00
     * @version 1.0
     */
    Integer updateRecommended(Recommended userRecommended);

    /**
     * @description: 添加评分
     * @author xiaoQe
     * @date 2023/4/2 17:41
     * @version 1.0
     */
    Integer insertUserRecommended(Recommended recommended);

    /**
     * @description: 寻找喜欢的twitter序号
     * @author xiaoQe
     * @date 2023/4/10 16:29
     * @version 1.0
     */
    ArrayList<TwitterSimilarity> selectLikeTwitterByUserId(Long userId);

    /**
     * @description: 查询所有未推荐过的文章Id
     * @author xiaoQe
     * @date 2023/4/12 14:54
     * @version 1.0
     */
    ArrayList<TwitterSimilarity> selectTwitterNotRecommended(Long userId);
}
