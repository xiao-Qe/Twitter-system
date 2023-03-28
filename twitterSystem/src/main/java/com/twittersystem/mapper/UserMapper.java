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

    Integer insertUser(User user);
}
