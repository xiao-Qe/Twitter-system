package com.twittersystem.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/30 16:11
 * 添加文章时所传入的类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertTwitter {
    //id
    private Long id;
    //分类id
    private Integer classifyId;
    //标题
    private String title;
    //类型
    private Integer type;
    //状态
    private Integer state;
    //作者id
    @NotNull
    private Long authorId;
    //简介
    private String blurb;
    //内容
    private String content;
}
