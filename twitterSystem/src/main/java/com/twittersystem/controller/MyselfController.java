package com.twittersystem.controller;

import com.twittersystem.bean.Constant;
import com.twittersystem.bean.ResBean;
import com.twittersystem.module.InsertTwitter;
import com.twittersystem.service.ITwitterService;
import com.twittersystem.utils.TwitterUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/2 17:36
 */
@RequestMapping("/myself")
@RestController
public class MyselfController {
    @Autowired
    private ITwitterService twitterService;

    @ApiOperation("添加文章")
    @PostMapping("/add_twitter")
    public ResBean addTwitter(@Valid @RequestBody InsertTwitter insertTwitter){
        //获取twitterId
        Long twitterId = TwitterUtil.getTwitterId(insertTwitter.getAuthorId());
        //给空属性赋值
        insertTwitter.setId(twitterId);
        insertTwitter.setState(Constant.TWITTER_STATE_AUDIT);

        Boolean success = twitterService.addTwitter(insertTwitter);
        if(success){
            return ResBean.ok("ok");
        }else {
            return ResBean.unauthorized("添加发生错误");
        }
    }
}
