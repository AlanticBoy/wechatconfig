package com.stronger.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  14:49 2019/1/13
 * @ModefiedBy:
 */
@XStreamAlias("xml")
public class ReceiveImgMsg extends Base{

    private String PicUrl;
    private String MediaId;

    @Override
    public String toString() {
        return "ReceiveImgMsg{" +
                "PicUrl='" + PicUrl + '\'' +
                ", MediaId='" + MediaId + '\'' +
                '}';
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
