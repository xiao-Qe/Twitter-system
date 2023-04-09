package com.twittersystem.controller;

import com.twittersystem.bean.ResBean;
import com.twittersystem.module.User;
import com.twittersystem.module.twitter.InsertTwitter;
import com.twittersystem.module.twitter.TwitterCard;
import com.twittersystem.module.twitter.UpdateTwitter;
import com.twittersystem.service.IUserService;
import com.twittersystem.utils.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/11 14:18
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("添加用户")
    @PostMapping("/add_user")
    public ResBean addUser(@Valid @RequestBody User user){
        Boolean addSuccess = userService.addUser(user.getUserName(), user.getPassword());
        if(addSuccess){
            return ResBean.ok("ok");
        }else {
            return ResBean.badRequest("添加出错");
        }
    }

    @ApiOperation("获取喜欢列表")
    @GetMapping("/get_like_list")
    public ResBean getLikeList(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JWTUtil.getUserIdFromToken(token);
        List<TwitterCard> likeList = userService.getLikeList(userId);
        return ResBean.ok("ok",likeList);
    }

    @ApiOperation("获取收藏列表")
    @GetMapping("/get_collect_list")
    public ResBean getCollectList(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JWTUtil.getUserIdFromToken(token);
        List<TwitterCard> collectList = userService.getCollectList(userId);
        return ResBean.ok("ok",collectList);
    }

    @ApiOperation("获取用户作品列表")
    @GetMapping("/get_myself_list")
    public ResBean getMyselfList(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JWTUtil.getUserIdFromToken(token);
        List<TwitterCard> collectList = userService.getMyselfList(userId);
        return ResBean.ok("ok",collectList);
    }

    @ApiOperation("获取修改展示类")
    @GetMapping("/get_update_twitter")
    public ResBean getUpdateTwitter(@NotNull @RequestParam("twitterId")Long twitterId){
        UpdateTwitter updateTwitterByTwitterId = userService.getUpdateTwitterByTwitterId(twitterId);
        if(updateTwitterByTwitterId == null){
            return ResBean.unauthorized("请求出错");
        }
        return ResBean.ok("ok",updateTwitterByTwitterId);
    }

    @ApiOperation("用户修改文章")
    @PostMapping("/set_twitter")
    public ResBean setTwitter(@Valid @RequestBody InsertTwitter insertTwitter){
        Boolean success = userService.setTwitter(insertTwitter);
        if(!success){
            return ResBean.unauthorized("请求错误，请重试");
        }
        return ResBean.ok("ok");
    }

    @ApiOperation("获取未通过原因")
    @GetMapping("/get_cause")
    public ResBean getCause(@NotNull @RequestParam("twitterId") Long twitterId){
        String cause = userService.getCause(twitterId);
        if(cause == null){
            return ResBean.unauthorized("请求错误，请重试");
        }
        return ResBean.ok("ok",cause);
    }
}
