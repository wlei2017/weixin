package com.example.weixin.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[text]]></MsgType>
 *   <Content><![CDATA[你好]]></Content>
 * </xml>
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage{

    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage(Map<String, String> reqmap,String content) {
        super(reqmap);
        this.setMsgType("text");
        this.content = content;
    }
}
