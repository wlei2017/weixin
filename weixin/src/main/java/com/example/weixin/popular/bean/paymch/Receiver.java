package com.example.weixin.popular.bean.paymch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class Receiver {

	private String type;

	private String account;

	private Integer amount;

	private String description;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	static class JsonXmlAdapter extends XmlAdapter<String, List<Receiver>> {

		@Override
		public String marshal(List<Receiver> v) throws Exception {
			return "<![CDATA[" + JSON.toJSONString(v) + "]]>";
		}

		@Override
		public List<Receiver> unmarshal(String v) throws Exception {
			return JSON.parseObject(v, new TypeReference<List<Receiver>>() {
			});
		}

	}
}
