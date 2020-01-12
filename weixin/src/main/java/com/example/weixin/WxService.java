package com.example.weixin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.weixin.baidu.Car;
import com.example.weixin.entity.*;
import com.example.weixin.popular.api.MediaAPI;
import com.example.weixin.popular.bean.media.Media;
import com.example.weixin.popular.bean.media.MediaType;
import com.example.weixin.twoBarCodes.GenerateCode;
import com.example.weixin.twoBarCodes.ImageUtils;
import com.example.weixin.util.HttpClientUtils;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import javax.xml.transform.stax.StAXResult;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxService {
    private static final String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
//    private static final String appsecret = "6905f07aa580f41b257217ed3f530f9a";
    private static final String appsecret = "12d187d5034b081ef9178134b085a5d9";
//    private static final String appID = "wx63618f66d11a1734";
    private static final String appID = "wxee016e086521fc0b";
    private static AccessToken at;
    //百度地图
    private static final String ak = "Z67KobvezLirc1Fghymv84aDeG4gGWsD";
    private static final String locationUrl = "http://api.map.baidu.com/reverse_geocoding/v3/?ak="+ ak +"&output=json&coordtype=wgs84ll&location=lat,lng";


    public static Map<String,String> parseXml(InputStream is){
        SAXReader reader = new SAXReader();
        Map<String,String> map = new HashMap<>();
        try {
            //读取输入流，获取文档对象
            Document document = reader.read(is);
            //根据文档对象获取根节点
            Element rootElement = document.getRootElement();
            //获取根节点下面的所以子节点
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                map.put(element.getName(),element.getStringValue());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 用于处理所有的事件和消息的回复
     * @param reqmap
     * @return
     */
    public static String getResponse(Map<String, String> reqmap) {
        String msgType = reqmap.get("MsgType");
        BaseMessage msg = null;
        switch (msgType){
            case "text":
                msg = dealTextMessage(reqmap);
                break;
            case "image":
                msg = dealImageMessage(reqmap);
                break;
            case "voice":
                msg = dealVoiceMessage(reqmap);
                break;
            case "video":
                msg = dealVideoMessage(reqmap);
                break;
            case "shortvideo":
                msg = dealMusicMessage(reqmap);
                break;
            case "location":
                break;
            case "link":
                break;
            case "event":
                msg = dealEventMessage(reqmap);
                break;
            default:
                break;
        }
        if (msg != null){

            return beanToXml(msg);
        }
        return null;
    }

    //处理event事件
    private static BaseMessage dealEventMessage(Map<String, String> reqmap) {
        String eventType = reqmap.get("Event");
        switch (eventType){
            //点击事件
            case "CLICK":
                return dealClick(reqmap);
                //跳转链接事件
            case "VIEW":
                return dealView(reqmap);
                //关注事件
            case "subscribe":
                return dealSubscribe(reqmap);
                //自定义菜单拍照或从相册选择
//            case "pic_photo_or_album":
//                return dealPicOrAlbum(reqmap);
//                //取消关注事件
            case "unsubscribe":
                break;
                //
            case "SCAN":
                break;
            case "LOCATION":
                return dealLocation(reqmap);
        }
        return null;
    }

//    /**
//     * 处理拍照或从相册选择
//     * @param reqmap
//     * @return
//     */
//    private static BaseMessage dealPicOrAlbum(Map<String, String> reqmap) {
//        String picUrl = reqmap.get("PicUrl");
//        String carResult = Car.car(picUrl);
//        JSONObject jsonObject = JSONObject.parseObject(carResult);
//        String color = jsonObject.getString("color_result");
//        JSONArray array = jsonObject.getJSONArray("result");
//        List<Map> lists = new ArrayList<>();
//        if (array != null){
//            lists = array.toJavaList(Map.class);
//        }
//        String carName = (String) lists.get(0).get("name");
//        TextMessage textMessage = new TextMessage(reqmap,carName);
//        return textMessage;
//    }

    /**
     * 处理上报地理位置信息
     * @param reqmap
     * @return
     */
    private static BaseMessage dealLocation(Map<String, String> reqmap) {
        //纬度
        String latitude = reqmap.get("Latitude");
        //经度
        String longitude = reqmap.get("Longitude");

        Map<String,Object> header = new HashMap<>();
        String jsonResult = null;
        try {
            jsonResult = HttpClientUtils.doGet(locationUrl.replace("lat", latitude).replace("lng", longitude), header);
            if (!StringUtils.isEmpty(jsonResult) && "0".equals(JSONObject.parseObject(jsonResult).getString("status")) ){
                String address = JSONObject.parseObject(jsonResult).getJSONObject("result").getString("formatted_address");
                return new TextMessage(reqmap,"您所在的位置是：" + address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 处理关注
     * @param reqmap
     * @return
     */
    private static BaseMessage dealSubscribe(Map<String, String> reqmap) {
        return new TextMessage(reqmap,"谢谢关注");
    }

    /**
     * 处理跳转事件
     * @param reqmap
     * @return
     */
    private static BaseMessage dealView(Map<String, String> reqmap) {
        return new TextMessage(reqmap,"跳转成功");
    }

    /**
     * 处理点击事件
     * @param reqmap
     * @return
     */
    private static BaseMessage dealClick(Map<String, String> reqmap) {
        //第一个点击事件 获取专属二维码
        if ("11".equals(reqmap.get("EventKey"))){
            try {
                InputStream inputStream = ImageUtils.genPosterToInputStream("aa", "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + GenerateCode.temporaryCode(reqmap.get("FromUserName")));
                Media media = MediaAPI.mediaUpload(at.getAccessToken(), MediaType.image, inputStream);
                Image image = new Image(media.getMedia_id());
                ImageMessage imageMessage = new ImageMessage(reqmap,image);
                return imageMessage;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if ("32".equals(reqmap.get("EventKey"))){
            return new TextMessage(reqmap,"你点击了二级菜单中的点击事件");
        } else if ("12".equals(reqmap.get("EventKey"))){
            //已邀请人数 根据二维码中的scene_str进行统计
            TextMessage textMessage = new TextMessage(reqmap,"您的推广人数为：0");
            return textMessage;
        }
        return null;
    }

    /**
     * 处理音乐类型消息
     * @param reqmap
     * @return
     */
    private static BaseMessage dealMusicMessage(Map<String,String> reqmap) {
        Music music = new Music("音乐","好听吗","kkkkk","ss","2");
        MusicMessage musicMessage = new MusicMessage(reqmap,music);
        return musicMessage;
    }


    /**
     * 处理视频消息
     * @param reqmap
     * @return
     */
    private static BaseMessage dealVideoMessage(Map<String,String> reqmap) {
        Video video = new Video("1","视频","测试");
        VideoMessage videoMessage = new VideoMessage(reqmap,video);
        return videoMessage;
    }

    /**
     * 处理语音消息
     * @param reqmap
     * @return
     */
    private static BaseMessage dealVoiceMessage(Map<String,String> reqmap) {
        Voice voice = new Voice("1");
        VoiceMessage voiceMessage = new VoiceMessage(reqmap,voice);
        return voiceMessage;
    }

    /**
     * 处理图片消息
     * @param reqmap
     * @return
     */
    private static BaseMessage dealImageMessage(Map<String,String> reqmap) {
        String picUrl = reqmap.get("PicUrl");
        String carResult = Car.car(picUrl);
        JSONObject jsonObject = JSONObject.parseObject(carResult);
        String color = jsonObject.getString("color_result");
        JSONArray array = jsonObject.getJSONArray("result");
        List<Map> lists = new ArrayList<>();
        if (array != null){
            lists = array.toJavaList(Map.class);
        }
        String carName = (String) lists.get(0).get("name");
        TextMessage textMessage = new TextMessage(reqmap,carName);
        return textMessage;
    }

    /**
     * 将消息对象转为xml
     * @param msg
     * @return
     */
    private static String beanToXml(BaseMessage msg) {
        XStream stream = new XStream();
        //设置需要处理
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        stream.processAnnotations(Article.class);
        String xml = stream.toXML(msg);
        System.out.println(xml);
        return xml;
    }

    /**
     * 处理文本消息
     * @param reqmap
     * @return
     */
    private static BaseMessage dealTextMessage(Map<String, String> reqmap) {
        String msg = reqmap.get("Content");
        if ("图文".equals(msg)){//模拟如果用户发送的是图文，则回复图文消息
            List<Article> articles = new ArrayList<>();
            Article article = new Article("哈喽，欢迎","风雨同舟","http://mmbiz.qpic.cn/mmbiz_jpg/yvZbm31rMX4N6HG1cmhs0fzUicgKO5asy928UFqzveZahppVYribT16jnV3fDd35HicgROz0LEGhxffjw1dYrS1hQ/0","www.baidu.com");
            articles.add(article);
            NewsMessage newsMessage = new NewsMessage(reqmap,articles);
            return newsMessage;
        }
        String content = chat(msg);
        TextMessage textMessage = new TextMessage(reqmap,content);
        return textMessage;
    }

    private static String chat(String msg) {
        Map<String, Object> header = new HashMap<>();
        String url = "http://open.drea.cc/bbsapi/chat/get?keyWord="+ msg +"&userName=type%3Dbbs";
        String responseStr = null;
        try {
            String s = HttpClientUtils.doGet(url, header);
            JSONObject jsonObject = JSONObject.parseObject(s);
            if (jsonObject == null || jsonObject.getJSONObject("data") == null){
                return "嘿嘿，有点意思";
            }
            responseStr = jsonObject.getJSONObject("data").getString("reply");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseStr;
    }

    /**
     * 获取access token
     */
    private static void getToken(){
        Map<String, Object> header = new HashMap<>();
        try {
            String get = HttpClientUtils.doGet(getTokenUrl.replace("APPID", appID).replace("APPSECRET", appsecret), header);
            //处理返回结果
            JSONObject jsonObject = JSONObject.parseObject(get);
            String accessToken = jsonObject.getString("access_token");
            String expireIn = jsonObject.getString("expires_in");
            at = new AccessToken(accessToken,expireIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向外暴露的获取token的方法
     * @return
     */
    public static String getAccessToken(){
        if (at == null || at.isExpire()){
            getToken();
        }
        return at.getAccessToken();
    }
}
