package com.twittersystem.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/29 18:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Twitter {
    //id
    private Long id;
    //分类id
    private Integer classifyId;
    //标题
    private String title;
    //类型
    private Integer type;
    //作者id
    private Long authorId;
    //简介
    private String blurb;
    //内容
    private String content;
    //观看
    private Integer view;
    //喜欢
    private Integer like;
    //收藏
    private Integer collect;
    //评分
    private Integer score;
}
