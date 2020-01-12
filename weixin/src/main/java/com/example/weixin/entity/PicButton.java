package com.example.weixin.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class PicButton extends AbButton {
    private String type = "pic_photo_or_album";
    private String key ;
    @JSONField(name = "sub_button")
    private List subButton = new ArrayList();

    public PicButton(String name, String key, List subButton) {
        super(name);
        this.key = key;
        this.subButton = subButton;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List getSubButton() {
        return subButton;
    }

    public void setSubButton(List subButton) {
        this.subButton = subButton;
    }
}
