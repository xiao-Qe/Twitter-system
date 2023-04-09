package com.twittersystem.module.twitter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/9 14:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTwitter {
    //id
    private Long id;
    //父类id
    private Integer superId;
    //分类id
    private Integer classifyId;
    //标题
    private String title;
    //类型
    private Integer type;
    //作者id
    @NotNull
    private Long authorId;
    //简介
    private String blurb;
    //内容
    private String content;
}
