package com.example.weixin.popular.bean.paymch;

import com.alibaba.fastjson.JSON;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class PromotionDetailXmlAdapter extends XmlAdapter<String, List<PromotionDetail>> {

	@Override
	public List<PromotionDetail> unmarshal(String v) throws Exception {
		String a = JSON.parseObject(v).getString("promotion_detail");
		return JSON.parseArray(a, PromotionDetail.class);
	}

	@Override
	public String marshal(List<PromotionDetail> v) throws Exception {
		return "<![CDATA[" + JSON.toJSONString(v) + "]]>";
	}
}
