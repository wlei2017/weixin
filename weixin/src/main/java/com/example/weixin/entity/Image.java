package com.example.weixin.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Image {
    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Image(String mediaId) {
        this.mediaId = mediaId;
    }
}
