package com.example.weixin.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[music]]></MsgType>
 *   <Music>
 *     <Title><![CDATA[TITLE]]></Title>
 *     <Description><![CDATA[DESCRIPTION]]></Description>
 *     <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
 *     <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
 *     <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
 *   </Music>
 * </xml>
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage{

    @XStreamAlias("Music")
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public MusicMessage(Map<String, String> reqmap, Music music) {
        super(reqmap);
        this.music = music;
        this.setMsgType("music");
    }
}
