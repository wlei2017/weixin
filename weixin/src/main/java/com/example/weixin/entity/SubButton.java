package com.example.weixin.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class SubButton extends AbButton{
    @JSONField(name = "sub_button")
    private List<AbButton> sbButton = new ArrayList<>();

    public SubButton(String name) {
        super(name);
    }

    public List<AbButton> getSbButton() {

        return sbButton;
    }

    public void setSbButton(List<AbButton> sbButton) {
        this.sbButton = sbButton;
    }
}
