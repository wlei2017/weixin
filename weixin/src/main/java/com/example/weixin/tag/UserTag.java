package com.example.weixin.tag;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.weixin.WxService;
import com.example.weixin.getUsers.GetUsers;
import com.example.weixin.util.HttpClientUtils;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTag {
    public static String createTag(){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxService.getAccessToken());
        Map<String,Map<String,String>> mapMap = new HashMap<>();
        Map<String,String> map = new HashMap();
        map.put("name","hello");
        mapMap.put("tag",map);
        String s = JSONObject.toJSONString(mapMap);
        try {
            String result = HttpClientUtils.sendHttpPost(url, s);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取所有标签
     * @return
     */
    public static String getTags(){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
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

    /**
     * 批量为用户打标签
     * @return
     */
    public static String bindTag(){
        JSONArray array = GetUsers.insertUser();
//        String tags = getTags();
//        JSONObject jsonObject = JSONObject.parseObject(tags);
//        JSONArray tags1 = jsonObject.getJSONArray("tags");
//        List<Map> maps = tags1.toJavaList(Map.class);

        Map<String,Object> map = new HashMap<>();
        List<String> list = array.toJavaList(String.class);
        map.put("openid_list",list);
        map.put("tagid",100);
        String param = JSONObject.toJSONString(map);
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",WxService.getAccessToken());
        try {
            String result = HttpClientUtils.sendHttpPost(url, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    /**
     * 群发消息
     * @return
     */
    public static String sendMessage(){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("is_to_all",false);
        map1.put("tag_id",100);
        map.put("filter",map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("media_id","FZJ7L-RR3vNXX9gbfwD1Ps42-AR9yEbCRlbEklMrPSF5eZ9eKNTyihwubVGkggz3");
        map.put("mpnews",map2);
        map.put("msgtype","mpnews");
        map.put("send_ignore_reprint",1);

        String param = JSONObject.toJSONString(map);
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",WxService.getAccessToken());
        try {
            String result = HttpClientUtils.sendHttpPost(url, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量为用户取消标签
     * @return
     */
    public static String cancleTag(){
        JSONArray array = GetUsers.insertUser();
        Map<String,Object> map = new HashMap<>();
        List<String> list = array.toJavaList(String.class);
        list.remove("oThQe1a_OlT-cBtQAoNLDaT5bnMw");
        map.put("openid_list",list);
        map.put("tagid",100);
        String param = JSONObject.toJSONString(map);
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",WxService.getAccessToken());
        try {
            String s = HttpClientUtils.sendHttpPost(url, param);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
