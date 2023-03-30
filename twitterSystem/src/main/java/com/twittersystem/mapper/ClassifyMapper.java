package com.twittersystem.mapper;

import com.twittersystem.module.Classify;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/29 17:31
 */
@Mapper
@Repository
public interface ClassifyMapper {
    /**
     * @description: 查询所有大分类
     * @author xiaoQe
     * @date 2023/3/29 17:34
     * @version 1.0
     */
    List<Classify> selectSuperClass();

    /**
     * @description: 根据大类id查询大类下的所有小分类
     * @author xiaoQe
     * @date 2023/3/29 17:52
     * @version 1.0
     */
    List<Classify> selectClassBySuperId(Integer superId);
}
