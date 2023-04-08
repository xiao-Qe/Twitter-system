package com.twittersystem.module.recommended;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/2 16:02
 * 用户对文章的评分
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    //文章id
    private Long twitterId;
    //用户id
    private Long userId;
    //评分
    private Integer grade;
}
