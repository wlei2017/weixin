package com.example.weixin.popular.bean.wxa;

import com.example.weixin.popular.bean.BaseResult;

import java.util.List;

public class GetPageResult extends BaseResult {

	private List<String> page_list;

	public List<String> getPage_list() {
		return page_list;
	}

	public void setPage_list(List<String> page_list) {
		this.page_list = page_list;
	}

}
