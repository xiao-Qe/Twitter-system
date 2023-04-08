package com.twittersystem.mapper;

import com.twittersystem.module.twitter.*;
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

    /**
     * @description: 获得审核文章列表
     * @author xiaoQe
     * @date 2023/4/5 17:41
     * @version 1.0
     */
    List<AuditTwitter> selectAuditTwitterList();

    /**
     * @description: 获得管理员页面展示
     * @author xiaoQe
     * @date 2023/4/5 18:37
     * @version 1.0
     */
    AdministratorShowTwitter selectAdministratorShowTwitter(Long twitterId);

    /**
     * @description: 管理员修改文章状态方法
     * @author xiaoQe
     * @date 2023/4/6 15:31
     * @version 1.0
     */
    Integer updateState(Long twitterId,Integer state);

    /**
     * @description: 用户获取喜欢列表
     * @author xiaoQe
     * @date 2023/4/8 17:13
     * @version 1.0
     */
    List<TwitterCard> selectLikeList(Long userId);

    /**
     * @description: 用户收藏列表
     * @author xiaoQe
     * @date 2023/4/8 17:28
     * @version 1.0
     */
    List<TwitterCard> selectCollectList(Long userId);
}
