package com.example.weixin.popular.bean.customservice;

import com.example.weixin.popular.bean.BaseResult;

import java.util.List;

/**
 * 客服会话
 * @author Menng
 */
public class KFSession extends BaseResult {
	private List<SessionList> sessionlist; // 会话列表

	public List<SessionList> getSessionlist() {
		return sessionlist;
	}

	public void setSessionlist(List<SessionList> sessionlist) {
		this.sessionlist = sessionlist;
	}

	public static class SessionList {
		private Integer createtime; // 会话创建时间，UNIX时间戳
		private String openid; // 客户openid

		public Integer getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Integer createtime) {
			this.createtime = createtime;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}
	}
}