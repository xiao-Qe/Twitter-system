package com.twittersystem.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/2 16:10
 * 文章评分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterScore {
    //文章id
    private Long twitterId;
    //喜欢
    private Integer like;
    //观看
    private Integer view;
    //收藏
    private Integer collect;
    //评分
    private Integer score;
}
