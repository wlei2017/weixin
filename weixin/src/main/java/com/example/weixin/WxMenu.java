package com.example.weixin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.weixin.entity.*;
import com.example.weixin.util.HttpClientUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建自定义微信菜单
 */
public class WxMenu {
    public static void main(String[] args) {
        //菜单对象
        Button button = new Button();
        //第一个一级菜单
        SubButton subButton1 = new SubButton("推广");
        subButton1.getSbButton().add(new ClickButton("获取专属二维码","11"));
        subButton1.getSbButton().add(new ClickButton("已邀请人数","12"));
        button.getButton().add(subButton1);
        //第二个一级菜单
        button.getButton().add(new ViewButton("跳转百度","http://www.baidu.com"));
        //二级菜单
        SubButton subButton = new SubButton("脏凯推荐");
        subButton.getSbButton().add(new PicButton("汽车识别","31",new ArrayList()));
        subButton.getSbButton().add(new ClickButton("JAVA学习","32"));

        //第三个一级菜单
        button.getButton().add(subButton);

        String jsonString = JSONObject.toJSONString(button);
        System.out.println(jsonString);
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        String accessToken = WxService.getAccessToken();

        Map<String, Object> header = new HashMap<>();
        try {
            url = url.replace("ACCESS_TOKEN",WxService.getAccessToken());
            String result = HttpClientUtils.sendHttpPost(url, jsonString);
//            String result = HttpClientUtils.post(url, jsonString);
//            String result = HttpClientUtils.doPost(url,jsonString,header);
            System.out.println("结果是"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
