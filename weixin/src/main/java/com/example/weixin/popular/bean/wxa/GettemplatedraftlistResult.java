package com.example.weixin.popular.bean.wxa;

import com.example.weixin.popular.bean.BaseResult;

import java.util.List;

public class GettemplatedraftlistResult extends BaseResult {

	private List<TemplateItem> drafttemplate_list;

	public List<TemplateItem> getDrafttemplate_list() {
		return drafttemplate_list;
	}

	public void setDrafttemplate_list(List<TemplateItem> drafttemplate_list) {
		this.drafttemplate_list = drafttemplate_list;
	}

}
