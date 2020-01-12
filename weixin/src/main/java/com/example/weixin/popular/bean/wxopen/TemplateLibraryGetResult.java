package com.example.weixin.popular.bean.wxopen;

import com.example.weixin.popular.bean.BaseResult;

import java.util.List;

public class TemplateLibraryGetResult extends BaseResult {

	private String id;

	private String title;

	private List<TemplateLibraryGetItem> keyword_list;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TemplateLibraryGetItem> getKeyword_list() {
		return keyword_list;
	}

	public void setKeyword_list(List<TemplateLibraryGetItem> keyword_list) {
		this.keyword_list = keyword_list;
	}

}
