package com.twittersystem.module.twitter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/1 16:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterDisplay {
    //id
    private Long id;
    //创建时间
    private Long creatTime;
    //标题
    private String title;
    //作者id
    private String author;
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
}
