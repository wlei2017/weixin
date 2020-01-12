package com.example.weixin.popular.bean.wxopen;

import com.example.weixin.popular.bean.BaseResult;

import java.util.List;

public class TemplateListResult extends BaseResult {

	private List<TemplateListItem> list;

	public List<TemplateListItem> getList() {
		return list;
	}

	public void setList(List<TemplateListItem> list) {
		this.list = list;
	}

}
