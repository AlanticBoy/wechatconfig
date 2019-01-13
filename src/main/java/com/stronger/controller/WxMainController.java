package com.stronger.controller;

import com.stronger.utis.DomUtils;
import com.stronger.utis.SignUtil;
import com.stronger.utis.XStreamUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  19:58 2019/1/12
 * @ModefiedBy:
 */
@Controller
@RequestMapping("/wxMain")
public class WxMainController {

    private static final String appid = "wx9c4a5d379c44d82e";//测试号appId

    private static final String secret = "cf14dab89d79230c80aad375525976e7";//测试号secret

    public static final String token = "tokenConfig";

    /* 保证服务器与微信服务器保持畅通。接收微信发来的消息。  */
    @RequestMapping("/checkSignuter")
    public void checkSignuter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("UTF-8");
        System.out.println("  check connect  ");
        PrintWriter pw = null;
        pw = response.getWriter();
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        //检查微信服务器和自己的服务器是否连接
        if (SignUtil.checkSignuture(token, timestamp, nonce, signature)) {
            pw.print(echostr);
        }
        System.out.println("  receive msg  ");
        //接收微信发来的消息
        String ret = null;
        try {
            request.setCharacterEncoding("UTF-8");
            InputStream inputStream=request.getInputStream();
            ret=XStreamUtils.interact(inputStream);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        pw.print(ret);
    }



}
