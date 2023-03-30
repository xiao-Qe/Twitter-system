package com.twittersystem.service.impl;

import com.twittersystem.mapper.ClassifyMapper;
import com.twittersystem.module.Classify;
import com.twittersystem.service.IClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/29 17:58
 */
@Service
public class ClassifyServiceImpl implements IClassifyService {
    @Autowired
    private ClassifyMapper classifyMapper;


    public List<Classify> getAllSuperClassify(){
        return classifyMapper.selectSuperClass();
    }

    @Override
    public List<Classify> getClassifyBySuperId(Integer superId) {
        return classifyMapper.selectClassBySuperId(superId);
    }
}
