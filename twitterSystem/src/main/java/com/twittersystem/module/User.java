package com.twittersystem.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/24 14:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
//    名字
    private String userName;
//    id号
    private Long userId;
//    密码
    private String password;
//    权限
    private Integer power;
}
