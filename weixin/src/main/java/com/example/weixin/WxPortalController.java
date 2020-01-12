//package com.example.weixin;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Arrays;
//
//
//@Slf4j
//@RestController
//@RequestMapping("/wx/portal")
//public class WxPortalController {
//
//    @GetMapping(produces = "text/plain;charset=utf-8")
//    public String authGet(@RequestParam(name="signature",required = false) String signature,
//                          @RequestParam(name="timestamp",required = false) String timestamp,
//                          @RequestParam(name="nonce",required = false) String nonce,
//                          @RequestParam(name="echostr",required = false) String echostr){
//        log.info("接收到来自微信服务器的认证消息：[{},{},{},{}]",signature,timestamp,nonce,echostr);
//        if (StringUtils.isAnyBlank(signature,timestamp,nonce,echostr)){
//            throw new IllegalArgumentException("请求参数非法，请核实！");
//        }
//        if (this.checkSignature(timestamp,nonce,signature)){
//            log.info("成功");
//            return echostr;
//        }
//        return "非法请求";
//    }
//
//    private boolean checkSignature(String timestamp, String nonce, String signature) {
//        String TOKEN = "wanglei";
//        String[] arr = {TOKEN,timestamp,nonce};
//        Arrays.sort(arr);
//        StringBuilder sb = new StringBuilder();
//        for (String a : arr) {
//            sb.append(a);
//        }
//        return DigestUtils.sha1Hex(sb.toString()).equals(signature);
//    }
//}
