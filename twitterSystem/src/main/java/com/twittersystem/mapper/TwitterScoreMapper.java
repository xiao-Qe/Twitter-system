package com.twittersystem.mapper;

import com.twittersystem.module.system.TwitterSimilarity;
import com.twittersystem.module.twitter.TwitterScore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/30 14:36
 */
@Mapper
@Repository
public interface TwitterScoreMapper {
    /**
     * @description: 添加数据接口
     * @author xiaoQe
     * @date 2023/4/2 15:43
     * @version 1.0
     */
    @Insert("insert into twitter_score (twitter_id,`view`,`like`,collect,score) values(#{id,jdbcType=BIGINT},0,0,0,300)")
    Integer insertScore(Long id);

    /**
     * @description: 查询文章评分接口
     * @author xiaoQe
     * @date 2023/4/2 16:13
     * @version 1.0
     */
    @Select("select twitter_id,`like`,`view`,collect,score from twitter_score where twitter_id = #{twitterId}")
    TwitterScore selectTwitterScore(Long twitterId);

    /**
     * @description: 点击文章增加文章评分
     * @author xiaoQe
     * @date 2023/4/2 16:08
     * @version 1.0
     */
    Integer updateTwitterScore(TwitterScore twitterScore);

    /**
     * @description: 查询所有文章
     * @author xiaoQe
     * @date 2023/4/10 16:23
     * @version 1.0
     */
    @Select("select id,twitter_id from twitter_score")
    ArrayList<TwitterSimilarity> selectAllTwitter();

    /**
     * @description: 查询作品推荐所需的信息
     * @author xiaoQe
     * @date 2023/4/11 16:37
     * @version 1.0
     */
    @Select("select id,twitter_id,`like` from twitter_score where id = #{id}")
    TwitterSimilarity selectById(Integer id);

    /**
     * @description: 查询评分最高的文章
     * @author xiaoQe
     * @date 2023/4/12 14:40
     * @version 1.0
     */
    List<Long> selectTwitterIdByScore(Long userId, Integer selectNum);
}
