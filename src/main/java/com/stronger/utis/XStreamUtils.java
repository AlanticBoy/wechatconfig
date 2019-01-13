package com.stronger.utis;

import com.stronger.commons.MessageType;
import com.stronger.entity.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  9:06 2019/1/13
 * @ModefiedBy:
 */
public class XStreamUtils {
    private static SAXReader reader = new SAXReader();
    private static String MSGTYPE = "MsgType";
    private static String CHARACTER_ENCODING = "UTF-8";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("config/receive");


    //接收微信消息并回送消息
    public static String interact(InputStream inputStream) throws DocumentException, IOException, ClassNotFoundException {
        //判断消息类型
        String ret = org.apache.commons.io.IOUtils.toString(inputStream, CHARACTER_ENCODING);

        InputStream is = org.apache.commons.io.IOUtils.toInputStream(ret, CHARACTER_ENCODING);
        //通过dom4j解析xml内容
        Document doc = reader.read(is);
        String type = doc.getRootElement().element(MSGTYPE).getText();
        //根据type得到class
        String key = "receive" + type;
        String classType = resourceBundle.getString(key);
        Class clazz = Class.forName(classType);
        XStream xStream = new XStream();
        xStream.processAnnotations(new Class[]{clazz});
        //通过一系列转换，把xml映射为实体类
        Object target = xStream.fromXML(ret);
        String result = null;
        switch (type) {
            case MessageType.REQ_MESSAGE_TYPE_TEXT:
                result = handleText(target);
                break;
            case MessageType.REQ_MESSAGE_TYPE_IMAGE:
                result = handleImage(target);
                break;
            case MessageType.REQ_MESSAGE_TYPE_VOICE:
                result = handleVoice();
                break;
            case MessageType.REQ_MESSAGE_TYPE_LOCATION:
                result = handleLocation();
                break;
            case MessageType.REQ_MESSAGE_TYPE_LINK:
                result = handleLink();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    /* 处理位置消息 */
    private static String handleLocation() {
        return null;
    }

    /* 处理视频消息 */
    private static String handleVideo() {
        return null;
    }

    /* 处理音频消息 */
    private static String handleVoice() {
        return null;
    }

    /* 处理图片消息 */
    private static String handleImage(Object obj) throws IOException {
        ReceiveImgMsg receiveImgMsg= (ReceiveImgMsg) obj;

        MediaUtils.download(receiveImgMsg.getMediaId());
        String mediaId=MediaUtils.upload();
        Image image=new Image();
        image.setMediaId(mediaId);

        ReplyImgMsg replyImgMsg=new ReplyImgMsg();

        replyImgMsg.setImage(image);
        replyImgMsg.setToUserName(receiveImgMsg.getFromUserName());
        replyImgMsg.setFromUserName(receiveImgMsg.getToUserName());
        replyImgMsg.setMsgType(receiveImgMsg.getMsgType());
        replyImgMsg.setCreateTime(receiveImgMsg.getCreateTime());

        XStream xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.processAnnotations(new Class[]{ReplyImgMsg.class});
        String ret = xStream.toXML(replyImgMsg);
        System.out.println("  object2Xml--->  " + ret);
        System.out.println("   mediaId---->   "+mediaId);
        return ret;
    }

    /* 处理链接消息 */
    private static String handleLink() {
        return null;
    }

    /* 处理文本消息 */
    private static String handleText(Object obj) {
        return object2Xml(obj);
    }

    private static String object2Xml(Object obj) {
        ReceiveTextMsg receiveTextMsg= (ReceiveTextMsg) obj;

        ReplyTextMsg replyTextMsg=new ReplyTextMsg();
        replyTextMsg.setToUserName(receiveTextMsg.getFromUserName());
        replyTextMsg.setMsgType(receiveTextMsg.getMsgType());
        replyTextMsg.setContent(receiveTextMsg.getContent());
        replyTextMsg.setCreateTime(receiveTextMsg.getCreateTime());
        replyTextMsg.setFromUserName(receiveTextMsg.getToUserName());

        XStream xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.processAnnotations(new Class[]{ReplyTextMsg.class});
        String ret = xStream.toXML(replyTextMsg);
        //System.out.println("  object2Xml--->  " + ret);
        return ret;
    }
}
