package com.example.weixin.popular.bean.wxa;

import com.example.weixin.popular.bean.BaseResult;

import java.util.List;

public class GettemplatelistResult extends BaseResult {

	private List<TemplateItem> template_list;

	public List<TemplateItem> getTemplate_list() {
		return template_list;
	}

	public void setTemplate_list(List<TemplateItem> template_list) {
		this.template_list = template_list;
	}

}
