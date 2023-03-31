package com.twittersystem.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/30 16:06
 * 返回推荐文章时所传出的类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterCard {
    //id
    private Long id;
    //标题
    private String title;
    //作者
    private String author;
    //简介
    private String blurb;
    //观看
    private Integer view;
    //喜欢
    private Integer like;
    //收藏
    private Integer collect;
}
