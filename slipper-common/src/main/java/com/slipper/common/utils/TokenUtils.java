package com.slipper.common.utils;

import com.slipper.common.exception.RunException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 生成token
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@ConfigurationProperties(prefix = "token")
@Component
public class TokenUtils {

    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    private static long expire;

    public static String generate() {
        return generate(UUID.randomUUID().toString());
    }

    public static String generate(String uuid) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(uuid.getBytes());
            byte[] digest = messageDigest.digest();
            String token = toHexString(digest);
            return token;
        } catch (Exception e) {
            throw new RunException("数据库中已存在该记录!", e);
        }
    }

    public static String toHexString(byte[] digest) {
        if(digest == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(digest.length*2);
        for ( byte b : digest) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        TokenUtils.expire = expire;
    }
}
