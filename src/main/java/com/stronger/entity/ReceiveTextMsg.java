package com.stronger.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  22:16 2019/1/12
 * @ModefiedBy:
 */
@XStreamAlias("xml")
public class ReceiveTextMsg extends Base {
    private  String Content;

    @Override
    public String toString() {
        return "ReceiveTestMsg{" +
                "Content='" + Content + '\'' +
                '}';
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
