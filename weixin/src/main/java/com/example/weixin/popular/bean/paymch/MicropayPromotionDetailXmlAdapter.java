package com.example.weixin.popular.bean.paymch;

import com.alibaba.fastjson.JSON;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MicropayPromotionDetailXmlAdapter extends XmlAdapter<String, MicropayPromotionDetail> {

	@Override
	public MicropayPromotionDetail unmarshal(String v) throws Exception {
		return JSON.parseObject(v, MicropayPromotionDetail.class);
	}

	@Override
	public String marshal(MicropayPromotionDetail v) throws Exception {
		return "<![CDATA[" + JSON.toJSONString(v) + "]]>";
	}

}
