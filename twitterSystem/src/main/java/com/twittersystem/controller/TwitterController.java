package com.twittersystem.controller;

import com.twittersystem.bean.Constant;
import com.twittersystem.bean.ResBean;
import com.twittersystem.module.Classify;
import com.twittersystem.module.InsertTwitter;
import com.twittersystem.module.TwitterDisplay;
import com.twittersystem.service.IClassifyService;
import com.twittersystem.service.ITwitterService;
import com.twittersystem.utils.JWTUtil;
import com.twittersystem.utils.TwitterUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/30 14:48
 */
@RequestMapping("/twitter")
@RestController
public class TwitterController {

    @Autowired
    private IClassifyService classifyService;

    @Autowired
    private ITwitterService twitterService;

    @ApiOperation("获取所有大分类id及分类名")
    @GetMapping("/get_super_id")
    public ResBean getSuperClass(HttpServletRequest request){
        List<Classify> superClassify = classifyService.getAllSuperClassify();
        return ResBean.ok("ok",superClassify);
    }

    @ApiOperation("获取每个分类下的小分类")
    @GetMapping("/get_classify_by_super_id")
    public ResBean getClassifyBySuperId(@NotNull @RequestParam("superId")Integer superId){
        List<Classify> classifyBySuperId = classifyService.getClassifyBySuperId(superId);
        return ResBean.ok("ok",classifyBySuperId);
    }

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

    @ApiOperation("获取文章展示内容")
    @GetMapping("/get_twitter_display")
    public ResBean getTwitterDisplay(@NotNull @RequestParam("twitterId")Long twitterId){
        TwitterDisplay twitterDisplay = twitterService.getTwitterDisplay(twitterId);
        if(twitterDisplay == null){
            return ResBean.unauthorized("发生异常，请重试");
        }else {
            return ResBean.ok("ok",twitterDisplay);
        }
    }
}
