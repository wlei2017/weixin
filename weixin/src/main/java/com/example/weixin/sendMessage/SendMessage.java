package com.example.weixin.sendMessage;

import com.alibaba.fastjson.JSONObject;
import com.example.weixin.WxService;
import com.example.weixin.sourceMaterial.Upload;
import com.example.weixin.util.HttpClientUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 群发消息
 */
public class SendMessage {

    /**
     * 上传图文消息内的图片获取URL
     * @return
     */
    public static String getUrl(){
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
        String token = WxService.getAccessToken();
        url = url.replace("ACCESS_TOKEN",token);
        String path = "C:\\Users\\wl\\Desktop\\11.jpg";
        try {
            JSONObject jsonObject = Upload.UploadMeida(path, url);
            if (jsonObject != null){

                String url1 = jsonObject.getString("url");

                return url1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return
     */
    public static String sendContent(){

        Article article = new Article();
        article.setAuthor("wanglei");
        article.setContent("无敌大脏凯");
        article.setContent_source_url("http://www.baidu.com");
        article.setNeed_open_comment(1);
        article.setOnly_fans_can_comment(0);
        article.setShow_cover_pic(1);
        article.setThumb_media_id("D06xeOu9X3U7qKdKNPnC_uK-8TgD8foia_pCwbMCmy27PHl4ycom_-_3ESgFbpM0");
        List<Article> articleList = new ArrayList<>();
        articleList.add(article);
        Articles articles = new Articles(articleList);
        String jsonParam = JSONObject.toJSONString(articles);
        String url1 = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
        url1 = url1.replace("ACCESS_TOKEN",WxService.getAccessToken());
        try {
            String s = HttpClientUtils.sendHttpPost(url1, jsonParam);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
