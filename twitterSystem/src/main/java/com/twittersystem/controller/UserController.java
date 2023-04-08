package com.twittersystem.controller;

import com.twittersystem.bean.ResBean;
import com.twittersystem.module.User;
import com.twittersystem.module.twitter.TwitterCard;
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
}
