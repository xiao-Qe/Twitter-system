package com.twittersystem.module.recommended;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/2 15:59
 * 已推荐的类    包含所有信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommended {
    //文章id
    private Long twitterId;
    //用户id
    private Long userId;
    //推荐
    private Boolean recommend;
    //观看
    private Boolean view;
    //喜欢
    private Boolean like;
    //收藏
    private Boolean collect;
    //评分
    private Integer grade;
}
