package com.twittersystem.controller;

import com.twittersystem.bean.ResBean;
import com.twittersystem.module.User;
import com.twittersystem.module.twitter.AdministratorShowTwitter;
import com.twittersystem.module.twitter.AuditTwitter;
import com.twittersystem.service.IAdministratorService;
import com.twittersystem.utils.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/5 17:52
 * 管理员审核
 */
@RequestMapping("/administrator")
@RestController
public class AdministratorController {

    @Autowired
    private IAdministratorService administratorService;

    @ApiOperation("获得待审核列表")
    @GetMapping("/get_audit_list")
    public ResBean getAuditList(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JWTUtil.getUserIdFromToken(token);
        List<AuditTwitter> auditTwitterList = administratorService.getAuditTwitterList(userId);
        if(auditTwitterList == null){
            return ResBean.unauthorized("无权限查看");
        }
        return ResBean.ok("ok",auditTwitterList);
    }

    @ApiOperation("获得文章具体信息")
    @GetMapping("/get_administrator_show_twitter")
    public ResBean getAdministratorShowTwitter(@NotNull @RequestParam("twitterId") Long twitterId,
                                               HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JWTUtil.getUserIdFromToken(token);
        AdministratorShowTwitter administratorShowTwitterByTwitterId = administratorService.getAdministratorShowTwitterByTwitterId(twitterId, userId);
        if(administratorShowTwitterByTwitterId == null){
            return ResBean.unauthorized("无权限查看");
        }
        return ResBean.ok("ok",administratorShowTwitterByTwitterId);
    }

    @ApiOperation("管理员通过文章")
    @GetMapping("/set_state_pass")
    public ResBean setTwitterStatePass(@NotNull @RequestParam("twitterId")Long twitterId,
                                       HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JWTUtil.getUserIdFromToken(token);
        Boolean success = administratorService.setTwitterStatePass(twitterId, userId);
        if(success == null){
            return ResBean.unauthorized("无权限查看");
        }
        return ResBean.ok("ok");
    }

    @ApiOperation("管理员下架文章")
    @GetMapping("/set_state_undercarriage")
    public ResBean setTwitterStateUndercarriage(@NotNull @RequestParam("twitterId")Long twitterId,
                                       @NotNull @RequestParam("cause")String cause,
                                       HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JWTUtil.getUserIdFromToken(token);
        Boolean success = administratorService.setTwitterStateUndercarriage(twitterId, userId,cause);
        if(success == null){
            return ResBean.unauthorized("无权限查看");
        }
        return ResBean.ok("ok");
    }

    @ApiOperation("获得用户列表")
    @GetMapping("/get_user_list")
    public ResBean getUserList(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JWTUtil.getUserIdFromToken(token);
        List<User> userList = administratorService.getUserList(userId);
        if (userList == null){
            return ResBean.unauthorized("无权限查看");
        }
        return ResBean.ok("ok",userList);

    }

    @ApiOperation("删除用户")
    @GetMapping("/delete_user")
    public ResBean deleteUser(@NotNull @RequestParam("userId")Long userId,
                              HttpServletRequest request){
        String token = request.getHeader("token");
        Long administratorId = JWTUtil.getUserIdFromToken(token);
        Boolean success = administratorService.deleteUser(userId, administratorId);
        if(success == null){
            return ResBean.unauthorized("无权限删除");
        }
        return ResBean.ok("ok");
    }

    @ApiOperation("修改用户")
    @PostMapping("/set_user")
    public ResBean setUserPassword(@RequestBody User user,
                              HttpServletRequest request){
        String token = request.getHeader("token");
        Long administratorId = JWTUtil.getUserIdFromToken(token);
        Boolean success = administratorService.setUser(administratorId,user);
        if(success == null){
                return ResBean.unauthorized("无权限删除");
        }
        return ResBean.ok("ok");
    }

}
