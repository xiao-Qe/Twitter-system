package com.twittersystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/28 17:29
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTUtil {
    //私钥
    private static String secret;
    //时间    单位小时
    private static Integer expire;

    public void setSecret(String secret){JWTUtil.secret = secret;}

    public void setExpire(Integer expire){JWTUtil.expire = expire;}

    /**
     * @description: 创建token
     * @author xiaoQe
     * @date 2023/3/28 17:46
     * @version 1.0
     */
    public static String creatToken(Long userId){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expire * 1000 * 60 * 60);
        return Jwts.builder()
                .setHeaderParam("type","JWT")   //设置请求头
                .setSubject(userId.toString())      //设置内容
                .setIssuedAt(now)                   //设置开始时间
                .setExpiration(expiration)          //设置结束时间
                .signWith(SignatureAlgorithm.HS512, secret)     //设置签名
                .compact();

    }

    /**
     * @description: 获取token中的信息
     * @author xiaoQe
     * @date 2023/3/29 14:48
     * @version 1.0
     */
    public static Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        long userId = Long.parseLong(claims.getSubject());
        return userId;
    }

    /**
     * @description: 校验token是否超时或错误 即是否需要更新
     * @author xiaoQe
     * @date 2023/3/29 14:48
     * @version 1.0
     */
    public static Boolean checkToken(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return false;
        }catch (Exception e){
            return true;
        }
    }
}
