package com.example.weixin.popular.bean.message;

import com.example.weixin.popular.bean.BaseResult;

import java.util.List;

public class GetAllPrivateTemplateResult extends BaseResult{

	private List<PrivateTemplate> template_list;

	public List<PrivateTemplate> getTemplate_list() {
		return template_list;
	}

	public void setTemplate_list(List<PrivateTemplate> template_list) {
		this.template_list = template_list;
	}
}
