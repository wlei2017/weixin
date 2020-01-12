package com.example.weixin.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class Button {
    @JSONField(name = "button")
    private List<AbButton> button = new ArrayList<>();

    public List<AbButton> getButton() {
        return button;
    }

    public void setButton(List<AbButton> button) {
        this.button = button;
    }
}
