package com.example.weixin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/wx/portal")
public class WxMessageController {

    @PostMapping()
    public String getMessage(HttpServletRequest request) throws Exception{
        ServletInputStream is = request.getInputStream();
//        byte[] b = new byte[1024];
//        int len;
//        StringBuilder sb = new StringBuilder();
//        while ((len = is.read(b))!= -1){
//            sb.append(new String(b,0,len));
//        }
//        System.out.println(sb.toString());

        //将xml文本转为map
        Map<String, String> reqmap = WxService.parseXml(is);
        System.out.println(reqmap);
        //准备回复的xml数据包
        String respXml = WxService.getResponse(reqmap);
        return respXml;
//        System.out.println(reqmap);
    }




}
