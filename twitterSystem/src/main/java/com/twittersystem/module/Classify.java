package com.twittersystem.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/29 17:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classify {
    //分类id
    private Integer classifyId;
    //分类名称
    private String className;
}
