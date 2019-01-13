package com.stronger.utis;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  15:21 2019/1/13
 * @ModefiedBy:
 */
public class GenerateTokenAndSignatureUtil {

    private static final String appid = "wx9c4a5d379c44d82e";//测试号appId

    private static final String secret = "cf14dab89d79230c80aad375525976e7";//测试号secret

    private static final String noncestr = "Wm3WZYTPz0wzccnW";

    //   获取access_token
    public static String getAccess_Token() {
        String rqeust_trl_acctoken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        try {
            URL url = new URL(rqeust_trl_acctoken);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpsURLConnection.getInputStream();
            String ret = IOUtils.toString(inputStream, "UTF-8");
            System.out.println("   "+ret);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(ret);
            return node.get("access_token").asText();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取Api_Ticket
    private String getApiTicket(String accesstoken) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accesstoken + "&type=jsapi";
        try {
            URL url1 = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            String ret = IOUtils.toString(inputStream, "UTF-8");
            inputStream.close();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(ret);
            return node.get("ticket").asText();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //生成signature
    public static String getSignature(String ticket, String url, int timeStamp) {
        String param = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timeStamp + "&url=" + url;
        return DigestUtils.sha1Hex(param);
    }

    public static int getSecondTimestampTwo(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return Integer.valueOf(timestamp);
    }

}
