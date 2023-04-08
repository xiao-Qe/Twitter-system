package com.twittersystem.module.twitter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/5 18:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorShowTwitter {
    //id
    private Long id;
    //父类Id
    private String superName;
    //分类id
    private String classifyName;
    //标题
    private String title;
    //类型
    private Integer type;
    //简介
    private String blurb;
    //内容
    private String content;
}
