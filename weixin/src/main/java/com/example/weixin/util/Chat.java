package com.example.weixin.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Chat {
    public static void main(String[] args) {
        Map<String, Object> header = new HashMap<>();
        Scanner in = new Scanner(System.in);
        System.out.println("请输入：");
        String content = in.nextLine();
        String url = "http://open.drea.cc/bbsapi/chat/get?keyWord="+ content +"&userName=type%3Dbbs";
        try {
            String s = HttpClientUtils.doGet(url, header);
            JSONObject jsonObject = JSONObject.parseObject(s);
            String string = jsonObject.getJSONObject("data").getString("reply");
            System.out.println(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
