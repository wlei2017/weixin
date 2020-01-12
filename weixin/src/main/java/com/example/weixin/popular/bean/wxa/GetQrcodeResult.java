package com.example.weixin.popular.bean.wxa;

import com.example.weixin.popular.bean.BaseResult;

import java.awt.image.BufferedImage;

public class GetQrcodeResult extends BaseResult{

	private BufferedImage bufferedImage;

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
}
