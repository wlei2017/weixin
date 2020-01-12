package com.example.weixin.mouldMessage;

import com.alibaba.fastjson.JSONObject;
import com.example.weixin.WxService;
import com.example.weixin.util.HttpClientUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送模板消息
 */
public class SendMould {
    public static void main(String[] args) {
        String wxUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        wxUrl = wxUrl.replace("ACCESS_TOKEN", WxService.getAccessToken());
        String toUser = "oThQe1a_OlT-cBtQAoNLDaT5bnMw";
        String templateId = "ALZGScNnLu1fvN8tGpL6iS3skqV6MOs55klel0g54gk";
        String url = "http://www.baidu.com";
        String first1 = "您的简历投递反馈来啦";
        String company1 = "大帅帅公司";
        String time1 = "2020年1月7日";
        String result1 = "恭喜您，通过啦";
        String remark1 = "请尽快与我们联系";
        Map<String,String> first = new HashMap<>();
        first.put("color","#173177");
        first.put("value",first1);
        Map<String,String> company = new HashMap<>();
        company.put("color","#173177");
        company.put("value",company1);
        Map<String,String> time = new HashMap<>();
        time.put("color","#173177");
        time.put("value",time1);
        Map<String,String> result = new HashMap<>();
        result.put("color","#173177");
        result.put("value",result1);
        Map<String,String> remark = new HashMap<>();
        remark.put("color","#173177");
        remark.put("value",remark1);
        Data data = new Data(first,company,time,result,remark);
        Template template = new Template(toUser,templateId,url,data);
        String s = JSONObject.toJSONString(template);
        try {
            String sendHttpPost = HttpClientUtils.sendHttpPost(wxUrl, s);
            System.out.println(sendHttpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
