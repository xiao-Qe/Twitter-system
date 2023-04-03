package com.twittersystem.mapper;

import com.twittersystem.module.recommended.Recommended;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

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
     * @description: 添加评分
     * @author xiaoQe
     * @date 2023/4/2 17:41
     * @version 1.0
     */
    Integer insertUserRecommended(Recommended recommended);
}
