package com.internation.info.vo;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.internation.info.model.MyCollection;
@Repository
public class professVo extends MyCollection implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
