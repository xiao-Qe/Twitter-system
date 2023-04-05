package com.twittersystem.service;

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
}
