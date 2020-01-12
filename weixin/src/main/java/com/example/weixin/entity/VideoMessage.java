package com.example.weixin.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[video]]></MsgType>
 *   <Video>
 *     <MediaId><![CDATA[media_id]]></MediaId>
 *     <Title><![CDATA[title]]></Title>
 *     <Description><![CDATA[description]]></Description>
 *   </Video>
 * </xml>
 */
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage{

    @XStreamAlias("Video")
    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public VideoMessage(Map<String, String> reqmap, Video video) {
        super(reqmap);
        this.video = video;
        this.setMsgType("video");
    }
}
