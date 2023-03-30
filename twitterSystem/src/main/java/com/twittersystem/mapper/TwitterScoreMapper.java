package com.twittersystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/30 14:36
 */
@Mapper
@Repository
public interface TwitterScoreMapper {
    @Insert("insert into twitter_score (twitter_id,`view`,`like`,collect,score) values(#{id,jdbcType=BIGINT},0,0,0,0)")
    Integer insertScore(Long id);
}
