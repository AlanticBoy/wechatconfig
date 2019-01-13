import com.stronger.entity.Base;
import com.stronger.entity.ReceiveTextMsg;
import com.stronger.utis.GenerateTokenAndSignatureUtil;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  21:32 2019/1/12
 * @ModefiedBy:
 */
public class Main {

   /* private static Map<String,String> typeMap=new HashMap<>();
    public static void main(String[] args) throws DocumentException, IOException, ClassNotFoundException {
        typeMap.put("receive","com.stronger.entity.ReceiveTextMsg");
        Class<?> receiveTextMsg=Class.forName(typeMap.get("receive"));

        String ret = "<xml><ToUserName><![CDATA[gh_a4745563c159]]></ToUserName>\n" +
                "<FromUserName><![CDATA[o9DJn6GW9XYO4SBaXaYZkRwzcmJs]]></FromUserName>\n" +
                "<CreateTime>1547298957</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[aaa]]></Content>\n" +
                "<MsgId>6645598417869456927</MsgId>\n" +
                "</xml>";
        String key="receive"+"";
        ResourceBundle resourceBundle=ResourceBundle.getBundle("config/receive");
        System.out.println(resourceBundle.getString(key));
        XStream xStream = new XStream();
        xStream.processAnnotations(new Class[]{receiveTextMsg});
        Object textMsg =  xStream.fromXML(ret);
        System.out.println(textMsg);
    }*/

    public static void main(String[] args) {

       /* try {
            URL url=new URL("https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=17_9M8cDI_yRL84lpx-6As1XzOmYGX5Mlpk0fT277lXd24OQCc5NEZYwfUhpTGRIQ1qjPDiReARrK7quQ4ftSedkUpthSwDtW1i2ICyoeVkz_0ul15fyMzRZq5-7eh5_eo5O_ddePFwmmCRNdOMLBPaAIABRF");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            InputStream inputStream=connection.getInputStream();
            System.out.println(IOUtils.toString(inputStream,"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println(GenerateTokenAndSignatureUtil.getAccess_Token());

    }


    //把对象转换为xml
    public static String object2Xml() {
        ReceiveTextMsg receiveTextMsg = new ReceiveTextMsg();
        receiveTextMsg.setToUserName("aaaa");
        receiveTextMsg.setContent("  nihaoya ");
        receiveTextMsg.setMsgType("ConTent");
        receiveTextMsg.setFromUserName("11111");
        XStream xStream = new XStream();
        xStream.processAnnotations(ReceiveTextMsg.class);
        String xml = xStream.toXML(receiveTextMsg);
        System.out.println("   xml----->   " + xml);
        return xml;
    }

    public static Object xml2Object() {
        String ret = "<xml><ToUserName><![CDATA[gh_a4745563c159]]></ToUserName>\n" +
                "<FromUserName><![CDATA[o9DJn6GW9XYO4SBaXaYZkRwzcmJs]]></FromUserName>\n" +
                "<CreateTime>1547298957</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[aaa]]></Content>\n" +
                "<MsgId>6645598417869456927</MsgId>\n" +
                "</xml>";

        XStream xStream = new XStream();
        xStream.processAnnotations(new Class[]{ReceiveTextMsg.class});
       // xStream.setMode(XStream.NO_REFERENCES);
        ReceiveTextMsg receiveTextMsg = (ReceiveTextMsg) xStream.fromXML(ret);
        return receiveTextMsg;
    }

    public static String getType() throws DocumentException, IOException {
        String ret = "<xml><ToUserName><![CDATA[gh_a4745563c159]]></ToUserName>\n" +
                "<FromUserName><![CDATA[o9DJn6GW9XYO4SBaXaYZkRwzcmJs]]></FromUserName>\n" +
                "<CreateTime>1547298957</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[aaa]]></Content>\n" +
                "<MsgId>6645598417869456927</MsgId>\n" +
                "</xml>";
        SAXReader reader = new SAXReader();
        File file = new File("D:\\ret.xml");
        FileUtils.writeStringToFile(file, ret);
        InputStream inputStream = FileUtils.openInputStream(file);
        Document document = reader.read(inputStream);

        String type = document.getRootElement().element("MsgType").getText();

        return type;
    }
}
