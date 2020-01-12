package com.example.weixin.popular.bean.user;

import com.example.weixin.popular.bean.BaseResult;

import java.util.List;

/**
 * 标签
 * 
 * @author LiYi
 * 
 */
public class TagsGetResult extends BaseResult {

	private List<Tag> tags;

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}
