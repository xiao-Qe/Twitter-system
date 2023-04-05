package com.twittersystem.controller;

import com.twittersystem.bean.ResBean;
import com.twittersystem.module.twitter.AuditTwitter;
import com.twittersystem.service.IAdministratorService;
import com.twittersystem.utils.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

}
