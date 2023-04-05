package com.twittersystem.mapper;

import com.twittersystem.module.twitter.InsertTwitter;
import com.twittersystem.module.twitter.TwitterCard;
import com.twittersystem.module.twitter.TwitterDisplay;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/29 18:15
 */
@Mapper
@Repository
public interface TwitterMapper {
    /**
     * @description: 添加文章
     * @author xiaoQe
     * @date 2023/4/1 16:23
     * @version 1.0
     */
    Integer insertTwitter(InsertTwitter insertTwitter,Long creatTime);

    /**
     * @description: 查询文章全部内容
     * @author xiaoQe
     * @date 2023/4/1 16:23
     * @version 1.0
     */
    TwitterDisplay selectTwitterDisplay(Long twitterId);

    /**
     * @description: 获取文章展示列表
     * @author xiaoQe
     * @date 2023/4/1 17:25
     * @version 1.0
     */
    List<TwitterCard> selectTwitterCardList();
}
