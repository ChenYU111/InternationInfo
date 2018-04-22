package com.internation.info.vo;

import java.io.Serializable;
import java.util.Date;

import com.internation.info.model.Review;

public class reviewVo extends Review implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	/**
	 * 创建时间
	 */
	private Date creaTime;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCreaTime() {
		return creaTime;
	}
	public void setCreaTime(Date creaTime) {
		this.creaTime = creaTime;
	}
	public String getRevertUserName() {
		return revertUserName;
	}
	public void setRevertUserName(String revertUserName) {
		this.revertUserName = revertUserName;
	}
	private String revertUserName;
	
	
}
