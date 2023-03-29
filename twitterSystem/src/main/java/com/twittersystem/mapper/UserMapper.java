package com.twittersystem.mapper;

import com.twittersystem.module.User;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/11 14:37
 */
@Mapper
@Repository
public interface UserMapper {
//    @Select("select * from user")
    List<User> selectTest();

    /**
     * @description: 用户注册，添加用户
     * @author xiaoQe
     * @date 2023/3/29 14:54
     * @version 1.0
     */
    Integer insertUser(User user);

    /**
     * @description: 用户登录查询接口
     * @author xiaoQe
     * @date 2023/3/29 15:32
     * @version 1.0
     */
    @Select("select user_id from user where user_id = #{userId} and password = MD5(#{password})")
    Long selectUser(Long userId,String password);

    @Select("select user_name,user_id from user where user_id = #{userId}")
    User selectFromUserId(Long userId);
}
