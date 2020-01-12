/**
 * 
 */
package com.example.weixin.popular.bean.shakearound.page.search;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.weixin.popular.bean.shakearound.page.PageInfo;

import java.util.List;

/**
 * 微信摇一摇周边－页面管理－查询页面列表－响应参数－页面列表数据
 * @author Moyq5
 * @date 2016年7月26日
 */
public class PageSearchResultData {

	/**
	 * 页面列表
	 */
	private List<PageInfo> pages;
	
	/**
	 * 商户名下的页面总数
	 */
	@JSONField(name = "total_count")
	private Integer totalCount;

	/**
	 * @return 页面列表
	 */
	public List<PageInfo> getPages() {
		return pages;
	}

	/**
	 * @param pages 页面列表
	 */
	public void setPages(List<PageInfo> pages) {
		this.pages = pages;
	}

	/**
	 * @return 商户名下的页面总数
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount 商户名下的页面总数
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
