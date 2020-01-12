package com.example.weixin.popular.bean.message.massmessage;

import com.example.weixin.popular.bean.message.preview.Preview;
import com.example.weixin.popular.bean.message.preview.TextPreview;

import java.util.HashMap;
import java.util.Map;

public class MassTextMessage extends MassMessage{

	private Map<String, String> text;

	public MassTextMessage(String content) {
		super();
		text = new HashMap<String, String>();
		text.put("content",content);
		super.msgtype = "text";
	}

	public Map<String, String> getText() {
		return text;
	}

	public void setText(Map<String, String> text) {
		this.text = text;
	}

	@Override
	public Preview convert() {
		Preview preview = new TextPreview(text.get("content"));
		if(this.getTouser()!=null && this.getTouser().size()>0){
			preview.setTouser(this.getTouser().iterator().next());
		}
		return preview;
	}

}
