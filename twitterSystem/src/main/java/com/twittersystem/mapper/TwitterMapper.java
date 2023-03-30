package com.twittersystem.mapper;

import com.twittersystem.module.InsertTwitter;
import com.twittersystem.module.TwitterInfo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/29 18:15
 */
@Mapper
@Repository
public interface TwitterMapper {
    Integer insertTwitter(InsertTwitter insertTwitter,Long creatTime);
}
