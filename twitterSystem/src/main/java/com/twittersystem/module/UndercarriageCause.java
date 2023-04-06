package com.twittersystem.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/6 16:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UndercarriageCause {
    //twitterID
    private Long twitterId;
    //原因
    private String cause;
}
