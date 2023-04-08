package com.twittersystem.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Calendar;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/30 16:23
 */
@Component
@ConfigurationProperties(prefix = "twitter")
public class TwitterUtil {

    //随机加密编码
    private static int[] encryptionArr;

    public void setEncryptionArr(int[] encryptionArr){TwitterUtil.encryptionArr = encryptionArr;}

    /**
     * @description: 加密Id
     * @author xiaoQe
     * @date 2023/3/30 16:24
     * @version 1.0
     */
    private static Long getEncryptionId(Long userId){
        Long returnLong = 0L;
        for (int j : encryptionArr) {
            returnLong = (returnLong + j) * 10L;
        }
        return userId + returnLong;
    }

    /**
     * @description: 获取twitter的Id
     * @author xiaoQe
     * @date 2023/3/30 16:50
     * @version 1.0
     */
    public static Long getTwitterId(Long userId){
        Long encryptionId = getEncryptionId(userId);
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        return encryptionId + timeInMillis;
    }
}
