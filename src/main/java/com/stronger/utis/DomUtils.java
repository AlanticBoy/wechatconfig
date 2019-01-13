package com.stronger.utis;

import com.stronger.entity.ReceiveTextMsg;
import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  20:43 2019/1/12
 * @ModefiedBy:
 */
public class DomUtils {
    public static ReceiveTextMsg parseXML(InputStream inputStream) throws DocumentException, InvocationTargetException, IllegalAccessException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element node = document.getRootElement();
        Iterator<Element> elementIterator = node.elementIterator();
        ReceiveTextMsg receiveTestMsg = new ReceiveTextMsg();
        Map<String,Object> paramMap=new HashMap<>();
        while (elementIterator.hasNext()) {
            Element element = elementIterator.next();
            paramMap.put(element.getName(),element.getText());
        }
        BeanUtils.populate(receiveTestMsg,paramMap);
        System.out.println("  receiveTestMsg  --->   "+receiveTestMsg);
        return receiveTestMsg;
    }
}
