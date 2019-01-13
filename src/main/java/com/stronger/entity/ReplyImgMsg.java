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

    private Image Image;


    public Image getImage() {
        return Image;
    }

    public void setImage(Image Image) {
        this.Image = Image;
    }
}
