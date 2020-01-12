package com.example.weixin.getUsers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.weixin.WxService;
import com.example.weixin.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class GetUsers{


    /**
     * 获取用户列表一次拉取调用最多拉取10000个关注者的OpenID
     * @param
     */
    public static JSONArray insertUser(){
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
        url = url.replace("ACCESS_TOKEN", WxService.getAccessToken()).replace("NEXT_OPENID","");

        Map<String,Object> header = new HashMap<>();
        try {
            String result = HttpClientUtils.doGet(url, header);
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray openids = data.getJSONArray("openid");
//            if (openids != null){
//
//                for (Object openid : openids) {
//                    // 根据openid查询用户详细信息，并存入数据库
//                    String url1 = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
//                    url1 = url1.replace("ACCESS_TOKEN",WxService.getAccessToken()).replace("OPENID",(String)openid);
//                    String s = HttpClientUtils.doGet(url1, header);
//                    JSONObject jsonObject1 = JSONObject.parseObject(s);
//                    User user = new User();
//                    user.setNickname(jsonObject1.getString("nickname"));
//                    user.setOpenid(jsonObject1.getString("openid"));
//                    user.setSubscribe(jsonObject1.getString("subscribe"));
//
//
//                }
//            }
//            System.out.println(openids);
            return openids;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
