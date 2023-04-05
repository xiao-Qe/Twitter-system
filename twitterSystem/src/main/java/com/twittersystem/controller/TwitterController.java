package com.twittersystem.controller;

import com.twittersystem.bean.ResBean;
import com.twittersystem.module.*;
import com.twittersystem.module.recommended.Recommended;
import com.twittersystem.module.twitter.TwitterCard;
import com.twittersystem.module.twitter.TwitterDisplay;
import com.twittersystem.service.IClassifyService;
import com.twittersystem.service.ITwitterService;
import com.twittersystem.service.IUserRecommendedService;
import com.twittersystem.utils.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
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

    @Autowired
    private IUserRecommendedService userRecommendedService;

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



    @ApiOperation("获取文章展示内容")
    @GetMapping("/get_twitter_display")
    public ResBean getTwitterDisplay(@NotNull @RequestParam("twitterId")Long twitterId,
                                     HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JWTUtil.getUserIdFromToken(token);
        TwitterDisplay twitterDisplay = twitterService.getTwitterDisplay(twitterId,userId);
        Recommended userRecommended = userRecommendedService.getUserRecommended(userId, twitterId);
        if(twitterDisplay == null || userRecommended == null){
            return ResBean.unauthorized("发生异常，请重试");
        }else {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("twitterDisplay",twitterDisplay);
            hashMap.put("userRecommended",userRecommended);
            return ResBean.ok("ok",hashMap);
        }
    }

    @ApiOperation("请求展示卡片")
    @GetMapping("/get_twitter_card")
    public ResBean getTwitterCard(){
        List<TwitterCard> twitterCardList = twitterService.getTwitterCardList();
        if(twitterCardList.isEmpty()){
            return ResBean.unauthorized("发生异常，请重试");
        }else {
            return ResBean.ok("ok",twitterCardList);
        }
    }

    @ApiOperation("点击喜欢和收藏方法")
    @PostMapping("/set_userRecommended")
    public ResBean setUserRecommended(@Valid @RequestBody Recommended userRecommended){
        Boolean success = twitterService.setUserRecommendedAndTwitterScoreByRecommended(userRecommended);
        if(success){
            return ResBean.ok("ok");
        }else {
            return ResBean.unauthorized("发生错误，请重试");
        }
    }
}
