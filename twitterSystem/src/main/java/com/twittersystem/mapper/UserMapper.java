package com.twittersystem.mapper;

import com.twittersystem.module.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select user_name,user_id,power from user where user_id = #{userId}")
    User selectFromUserId(Long userId);

    /**
     * @description: 管理员查看用户方法
     * @author xiaoQe
     * @date 2023/4/6 17:05
     * @version 1.0
     */
    @Select("select user_name,user_id from user where power = 0")
    List<User> selectUserList();

    /**
     * @description: 管理员删除用户接口
     * @author xiaoQe
     * @date 2023/4/6 17:34
     * @version 1.0
     */
    @Delete("delete from user where user_id = #{userId}")
    Integer deleteUser(Long userId);

    /**
     * @description: 管理员修改用户密码
     * @author xiaoQe
     * @date 2023/4/6 17:45
     * @version 1.0
     */
    Integer updateUser(User user);

    @Select("select user_id from user")
    List<Long> selectAllUser();
}
