package com.twittersystem.service;

import com.twittersystem.module.User;
import com.twittersystem.module.twitter.AdministratorShowTwitter;
import com.twittersystem.module.twitter.AuditTwitter;

import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/5 17:48
 */
public interface IAdministratorService {

    /**
     * @description: 获得审核集合
     * @author xiaoQe
     * @date 2023/4/5 17:49
     * @version 1.0
     */
    List<AuditTwitter> getAuditTwitterList(Long userId);

    /**
     * @description: 获得详细内容并展示
     * @author xiaoQe
     * @date 2023/4/6 15:11
     * @version 1.0
     */
    AdministratorShowTwitter getAdministratorShowTwitterByTwitterId(Long twitterId,Long userId);

    /**
     * @description: 管理员通过文章
     * @author xiaoQe
     * @date 2023/4/6 15:35
     * @version 1.0
     */
    Boolean setTwitterStatePass(Long twitterId,Long userId);

    /**
     * @description: 管理员下架文章
     * @author xiaoQe
     * @date 2023/4/6 15:38
     * @version 1.0
     */
    Boolean setTwitterStateUndercarriage(Long twitterId,Long userId,String cause);

    /**
     * @description: 管理员获得用户列表
     * @author xiaoQe
     * @date 2023/4/6 17:09
     * @version 1.0
     */
    List<User> getUserList(Long userId);

    /**
     * @description: 删除用户
     * @author xiaoQe
     * @date 2023/4/6 17:48
     * @version 1.0
     */
    Boolean deleteUser(Long userId,Long administratorId);

    /**
     * @description: 修改用户密码
     * @author xiaoQe
     * @date 2023/4/6 17:50
     * @version 1.0
     */
    Boolean setUser(Long userId,User user);
}
