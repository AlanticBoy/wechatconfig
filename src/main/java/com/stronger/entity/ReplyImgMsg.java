package com.stronger.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  14:49 2019/1/13
 * @ModefiedBy:
 */
@XStreamAlias("xml")
public class ReplyImgMsg  extends Base{

    private Image image;


    @Override
    public String toString() {
        return "ReplyImgMsg{" +
                "image=" + image +
                '}';
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
