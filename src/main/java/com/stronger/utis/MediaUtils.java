package com.stronger.utis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  15:56 2019/1/13
 * @ModefiedBy:
 */
public class MediaUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /* 根据mediaId把多媒体文件下载到本地*/
    public static void download(String mediaId) throws IOException {
        String token = GenerateTokenAndSignatureUtil.getAccess_Token();
        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + token + "&media_id=" + mediaId;
        URL url1 = new URL(url);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url1.openConnection();
        urlConnection.setRequestMethod("GET");
        InputStream inputStream = urlConnection.getInputStream();

        FileUtils.copyInputStreamToFile(inputStream, new File("F:\\test.png"));

    }

    /* 把多媒体文件上传到微信服务器*/
    public static String upload() throws IOException {

        //准备前期
        File file = new File("F:\\test.png");
        URL url = new URL("https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + GenerateTokenAndSignatureUtil.getAccess_Token() + "&type=image");
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setRequestProperty("Connection", "Keep-Alive");
        urlConnection.setRequestProperty("Charset", "UTF-8");
        //声明分割线
        String boundary = "----------" + System.currentTimeMillis();
        urlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(boundary);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");
        //获得输出流
        OutputStream out = new DataOutputStream(urlConnection.getOutputStream());
        //输出表头
        out.write(head); //文件正文部分 //把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        //结尾部分
        byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");
        //定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();

        InputStream is = urlConnection.getInputStream();
        String result = IOUtils.toString(is, "UTF-8");
        return objectMapper.readTree(result).get("media_id").asText();
    }

}
