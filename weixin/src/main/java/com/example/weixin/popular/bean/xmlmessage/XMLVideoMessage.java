package com.example.weixin.popular.bean.xmlmessage;

import com.example.weixin.popular.bean.message.message.Message;
import com.example.weixin.popular.bean.message.message.VideoMessage;
import com.example.weixin.popular.bean.message.message.VideoMessage.Video;

public class XMLVideoMessage extends XMLMessage {

	private static final long serialVersionUID = 3858730703846989272L;

	private String mediaId;

	private String title;

	private String description;

	public XMLVideoMessage(String toUserName, String fromUserName, String mediaId, String title, String description) {
		super(toUserName, fromUserName, "video");
		this.mediaId = mediaId;
		this.title = title;
		this.description = description;
	}

	@Override
	public String subXML() {
		return "<Video><MediaId><![CDATA["+mediaId+"]]></MediaId><Title><![CDATA["+(title==null?"":title)+"]]></Title><Description><![CDATA["+(description==null?"":description)+"]]></Description></Video>";
	}

	@Override
	public Message convert() {
		Video video = new Video(mediaId, title, description);
		return new VideoMessage(toUserName, video);
	}

}
