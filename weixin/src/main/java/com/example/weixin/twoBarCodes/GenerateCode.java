package com.example.weixin.twoBarCodes;

import com.alibaba.fastjson.JSONObject;
import com.example.weixin.WxService;
import com.example.weixin.util.HttpClientUtils;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码
 */
public class GenerateCode {

    /**
     * 生成临时二维码ticket
     * @return
     */
    public static String temporaryCode(String sceneStr){
        String expireSeconds = "6000";
        String actionName = "QR_STR_SCENE";
        Map<String,Map<String,String>> actionInfo = new HashMap<>();
        Map<String,String> map = new HashMap<>();
        map.put("scene_str",sceneStr);
        actionInfo.put("scene",map);

        Code code = new Code(expireSeconds,actionName,actionInfo);

        String param = JSONObject.toJSONString(code);

        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
        url = url.replace("TOKEN", WxService.getAccessToken());
        try {
            String result = HttpClientUtils.sendHttpPost(url, param);

            JSONObject jsonObject = JSONObject.parseObject(result);
            String ticket = jsonObject.getString("ticket");

            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
