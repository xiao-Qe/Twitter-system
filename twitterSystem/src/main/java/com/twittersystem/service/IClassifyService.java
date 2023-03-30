package com.twittersystem.service;

import com.twittersystem.module.Classify;

import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/29 17:58
 */
public interface IClassifyService {
    /**
     * @description: 查询所有分类
     * @author xiaoQe
     * @date 2023/3/30 14:41
     * @version 1.0
     */
    public List<Classify> getAllSuperClassify();

    /**
     * @description: 根据大类id查询所有子类
     * @author xiaoQe
     * @date 2023/3/30 14:46
     * @version 1.0
     */
    public List<Classify> getClassifyBySuperId(Integer superId);
}
