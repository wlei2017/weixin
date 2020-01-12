package com.example.weixin.entity;

import java.util.List;

public class ViewButton extends AbButton {
    private String type = "view";
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ViewButton(String name, String url) {
        super(name);
        this.url = url;
    }
}
