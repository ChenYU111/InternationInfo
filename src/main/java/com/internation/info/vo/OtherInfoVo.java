package com.internation.info.vo;

import java.io.Serializable;

import com.internation.info.model.OtherInfo;

public class OtherInfoVo extends OtherInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String StrContent;

	public String getStrContent() {
		return StrContent;
	}

	public void setStrContent(String strContent) {
		StrContent = strContent;
	}
}
