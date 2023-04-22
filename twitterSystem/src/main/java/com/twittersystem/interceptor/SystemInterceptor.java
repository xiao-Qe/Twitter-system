package com.twittersystem.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twittersystem.bean.ResBean;
import com.twittersystem.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/4/22 14:13
 */
@Slf4j
@Component
public class SystemInterceptor implements HandlerInterceptor {

    private final ObjectMapper mapper;

    @Autowired
    public SystemInterceptor(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getMethod().equals("OPTIONS")) {
            String token = request.getHeader("token");
            if (token == null || token.equals("")) {
                returnJSON(response, mapper.writeValueAsString(ResBean.badRequest(1000, "未登录请先登录")));
                return false;
            }
            Boolean checkToken = JWTUtil.checkToken(token);
            if (checkToken) {
                returnJSON(response, mapper.writeValueAsString(ResBean.badRequest(1000, "登录失效，请重新登录")));
                return false;
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
    /**
     * @description: 返回json格式
     * @author xiaoQe
     * @date 2023/4/22 14:15
     * @version 1.0
     */
    private void returnJSON(HttpServletResponse response, String json){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;character=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(json);
        } catch (IOException e) {
            log.error("response error", e);
        }
    }
}
