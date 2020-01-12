package com.example.weixin.baidu;

import java.net.URLEncoder;

public class Car {


    public static String car(String imageUrl) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/car";
        try {
//            // 本地文件路径
//            String filePath = "E:\\aaa.jpg";
//            byte[] imgData = FileUtil.readFileByBytes(filePath);
            byte[] imgData = FileUtil.getFile(imageUrl);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam + "&top_num=" + 5;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

//            String result = ;
//            System.out.println(result);
            return HttpUtil.post(url, accessToken, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
