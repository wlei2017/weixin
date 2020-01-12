package com.example.weixin.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 *  <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[voice]]></MsgType>
 *   <Voice>
 *     <MediaId><![CDATA[media_id]]></MediaId>
 *   </Voice>
 * </xml>
 */
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage{

    @XStreamAlias("Voice")
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public VoiceMessage(Map<String, String> reqmap, Voice voice) {
        super(reqmap);
        this.voice = voice;
        this.setMsgType("voice");
    }
}
