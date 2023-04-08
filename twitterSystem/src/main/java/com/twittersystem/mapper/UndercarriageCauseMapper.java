package com.twittersystem.mapper;

import com.twittersystem.module.UndercarriageCause;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/6 16:32
 */
@Mapper
@Repository
public interface UndercarriageCauseMapper {
    /**
     * @description: 添加未通过记录
     * @author xiaoQe
     * @date 2023/4/6 16:33
     * @version 1.0
     */
    @Insert("insert into undercarriage_cause values(#{twitterId},#{cause})")
    Integer insetUndercarriageCause(Long twitterId,String cause);

    /**
     * @description: 查询未通过
     * @author xiaoQe
     * @date 2023/4/6 16:38
     * @version 1.0
     */
    @Select("select twitter_id,cause from undercarriage_cause where twitter_id = #{twitterId}")
    UndercarriageCause selectUndercarriageCause(Long twitterId);

    /**
     * @description: 修改未通过
     * @author xiaoQe
     * @date 2023/4/6 16:41
     * @version 1.0
     */
    @Update("update undercarriage_cause set cause = #{cause} where twitter_id = #{twitterId}")
    Integer updateUndercarriageCause(Long twitterId,String cause);
}
