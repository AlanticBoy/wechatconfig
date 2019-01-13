package com.stronger.utis;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  20:04 2019/1/12
 * @ModefiedBy:
 */
public class SignUtil {
    public static boolean checkSignuture(String token, String timestamp, String nonce, String signature) {
        String array[] = {token, timestamp, nonce};
        Arrays.sort(array);
        StringBuffer buffer = new StringBuffer();
        for (String param : array) {
            buffer.append(param);
        }
        String ret = DigestUtils.sha1Hex(buffer.toString());
        return signature.equals(ret);
    }
}
