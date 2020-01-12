package com.example.weixin.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.Map;

/**
 *  <xml>
 *  <ToUserName><![CDATA[toUser]]></ToUserName>
 *  <FromUserName><![CDATA[fromUser]]></FromUserName>
 *  <CreateTime>12345678</CreateTime>
 *  <MsgType><![CDATA[image]]></MsgType>
 *  <Image>
 *  <MediaId><![CDATA[media_id]]></MediaId>
 *  </Image>
 *  </xml>
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{
   @XStreamAlias("Image")
   private Image image;

    public ImageMessage(Map<String, String> reqmap, Image image) {
        super(reqmap);
        this.image = image;
        this.setMsgType("image");
    }
}
