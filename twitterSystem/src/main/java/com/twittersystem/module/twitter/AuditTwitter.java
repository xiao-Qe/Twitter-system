package com.twittersystem.module.twitter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/5 17:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditTwitter {
    //id
    private Long twitterId;
    //标题
    private String title;
    //分类
    private String classifyName;
    //作者
    private String author;
    //创建时间
    private Long creatTime;
    //状态
    private Integer state;
    //类型
    private Integer type;
}
