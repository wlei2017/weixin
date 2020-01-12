package com.example.weixin.mouldMessage;

import com.alibaba.fastjson.JSONObject;
import com.example.weixin.WxService;
import com.example.weixin.util.HttpClientUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置所属行业
 */
public class SetIndustry {
    public static void main(String[] args) {
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",WxService.getAccessToken());
        System.out.println(url);
        Industry industry = new Industry("1","2");
        String jsonString = JSONObject.toJSONString(industry);
        try {
            String result = HttpClientUtils.sendHttpPost(url, jsonString);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getIndustry(){
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",WxService.getAccessToken());
        Map<String,Object> header = new HashMap<>();
        try {
            String s = HttpClientUtils.doGet(url, header);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
