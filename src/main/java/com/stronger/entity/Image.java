package com.stronger.entity;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  16:37 2019/1/13
 * @ModefiedBy:
 */
public class Image {
    private String MediaId;

    @Override
    public String toString() {
        return "Image{" +
                "MediaId='" + MediaId + '\'' +
                '}';
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
