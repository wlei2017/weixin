package com.example.weixin;

import com.alibaba.fastjson.JSONObject;
import com.example.weixin.baidu.AuthService;
import com.example.weixin.baidu.Car;
import com.example.weixin.entity.*;
import com.example.weixin.getUsers.GetUsers;
import com.example.weixin.mouldMessage.Data;
import com.example.weixin.mouldMessage.SetIndustry;
import com.example.weixin.mouldMessage.Template;
import com.example.weixin.popular.api.MediaAPI;
import com.example.weixin.popular.bean.media.Media;
import com.example.weixin.popular.bean.media.MediaType;
import com.example.weixin.sendMessage.Article;
import com.example.weixin.sendMessage.Articles;
import com.example.weixin.sendMessage.SendMessage;
import com.example.weixin.sourceMaterial.Upload;
import com.example.weixin.tag.UserTag;
import com.example.weixin.twoBarCodes.GenerateCode;
import com.example.weixin.twoBarCodes.ImageUtils;
import com.example.weixin.util.HttpClientUtils;
import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.*;

@SpringBootTest
class WeixinApplicationTests {

    @Test
    void contextLoads() {
        Map<String,String> map = new HashMap<>();
        map.put("ToUserName","aa");
        map.put("FromUserName","wanglei");
        TextMessage textMessage = new TextMessage(map,"我是好人");
        XStream stream = new XStream();
        //设置需要处理
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        String xml = stream.toXML(textMessage);
        System.out.println(xml);
    }

    @Test
    void test(){
        String accessToken = WxService.getAccessToken();
        System.out.println(accessToken);
    }

    @Test
    void testLocation(){
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=Z67KobvezLirc1Fghymv84aDeG4gGWsD&output=json&coordtype=wgs84ll&location=31.225696563611,121.49884033194";
        Map<String,Object> header = new HashMap<>();
        try {
            String s = HttpClientUtils.doGet(url, header);
            String string = JSONObject.parseObject(s).getJSONObject("result").getString("formatted_address");
            System.out.println(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testBaiduAuth(){
        String auth = AuthService.getAuth();
        System.out.println("*********" + auth);
    }

    @Test
    void testCar(){
        String url = "http://mmbiz.qpic.cn/mmbiz_jpg/yvZbm31rMX5L2XicXSN6793lxJibyMLE9UtwGLzFh7KDgeyGAfsdJUwzOcFaibicxnpYHIJkNiaaKicIWB2iaQ3sm0v0w/0";
        String car = Car.car(url);
        System.out.println(car);
    }

    @Test
    void testGetIndustry(){
        String industry = SetIndustry.getIndustry();
        System.out.println(industry);
    }

    @Test
    void testTemplateJson(){
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
        System.out.println(s);
    }

    @Test
    void testUpload(){
        String path = "C:\\Users\\wl\\Desktop\\aa.png";
        try {
            JSONObject jsonObject = Upload.UploadMeida( path,"");
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试二维码
     */
    @Test
    void testCode(){
        String s = GenerateCode.temporaryCode("dd");
        System.out.println(s);
    }

    @Test
    void testUp(){
        try {
//            JSONObject jsonObject = Upload.UploadMeidaWithUrl("image");
//            System.out.println(jsonObject);
            InputStream inputStream = ImageUtils.genPosterToInputStream("aa", "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + GenerateCode.temporaryCode("aa"));

            String token = WxService.getAccessToken();
            System.out.println( token);
            Media image = MediaAPI.mediaUpload(token, MediaType.image, inputStream);
            System.out.println(image.getMedia_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInsert(){
        GetUsers getUsers = new GetUsers();
        getUsers.insertUser();

    }

    @Test
    void testSendMsgUrl(){
        String url = SendMessage.getUrl();
        System.out.println(url);
    }

    @Test
    void testJson(){
        Article article = new Article("d","dd","aa","d","d","s",1,2,3);
        List<Article> articles1 = new ArrayList<>();
        articles1.add(article);
        Articles articles = new Articles(articles1);
        String s = JSONObject.toJSONString(articles);
        System.out.println(s);
    }

    /**
     * 上传临时素材
     */
    @Test
    void testTemporary(){
        String path = "C:\\Users\\wl\\Desktop\\11.jpg";
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        url = url.replace("ACCESS_TOKEN", WxService.getAccessToken()).replace("TYPE", "image");

        try {
            JSONObject jsonObject = Upload.UploadMeida(path, url);
            System.out.println(jsonObject);
            System.out.println(jsonObject.getString("media_id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test3(){
        String s = SendMessage.sendContent();
        System.out.println(s);
    }

    @Test
    void testTagCreate(){
        String tag = UserTag.createTag();
        System.out.println(tag);
    }


    @Test
    void testTagGet(){
        String tag = UserTag.getTags();
        System.out.println(tag);
    }

    @Test
    void testTagBind(){
        String s = UserTag.bindTag();
        System.out.println(s);
    }
    @Test
    void testTagCancle(){
        String s = UserTag.cancleTag();
        System.out.println(s);
    }

    @Test
    void testSendMessage(){
        String s = UserTag.sendMessage();
        System.out.println(s);
    }
}
