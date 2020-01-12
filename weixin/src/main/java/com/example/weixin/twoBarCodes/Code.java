package com.example.weixin.twoBarCodes;

import com.alibaba.fastjson.annotation.JSONType;

import java.util.Map;

/**
 * 临时二维码
 */

@JSONType(orders = {"expire_seconds","action_name","action_info"})
public class Code {
    private String expire_seconds;
    private String action_name;
    private Map<String,Map<String,String>> action_info;

    public Code(String expire_seconds, String action_name, Map<String, Map<String, String>> action_info) {
        this.expire_seconds = expire_seconds;
        this.action_name = action_name;
        this.action_info = action_info;
    }

    public String getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(String expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public Map<String, Map<String, String>> getAction_info() {
        return action_info;
    }

    public void setAction_info(Map<String, Map<String, String>> action_info) {
        this.action_info = action_info;
    }
}
