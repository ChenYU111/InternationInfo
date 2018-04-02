package com.internation.info.vo;

import com.internation.info.model.Answer;

public class answerVo extends Answer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private Integer uId;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}
}
