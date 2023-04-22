package com.twittersystem.module.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/10 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterSimilarity {
    //序号
    private Integer id;
    //twitterId
    private Long twitterId;
    //相似度
    private Double W;
    //喜欢人数
    private Integer Like;
}
