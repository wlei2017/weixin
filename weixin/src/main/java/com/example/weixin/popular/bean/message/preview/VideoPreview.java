package com.example.weixin.popular.bean.message.preview;

import com.example.weixin.popular.bean.message.Uploadvideo;

public class VideoPreview extends Preview {

	private Uploadvideo video;

	public VideoPreview(){

	}
	public VideoPreview(Uploadvideo video) {
		super();
		this.setMsgtype("video");
		this.video = video;
	}

	public Uploadvideo getVideo() {
		return video;
	}

	public void setVideo(Uploadvideo video) {
		this.video = video;
	}

}
