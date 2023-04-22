package com.twittersystem.module.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/12 15:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserScore {
    private Long twitterId;
    private Integer grade;
}
