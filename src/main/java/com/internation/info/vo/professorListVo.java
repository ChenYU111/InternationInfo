package com.internation.info.vo;

import java.io.Serializable;

import com.internation.info.model.User;

public class professorListVo extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
