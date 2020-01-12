package com.example.weixin.popular.bean.comment;

import com.example.weixin.popular.bean.BaseResult;

import java.util.List;

public class CommentListResult extends BaseResult {

	private Long total;

	List<Comment> comment;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

}
