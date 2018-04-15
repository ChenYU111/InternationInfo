package com.internation.info.vo;

import org.springframework.stereotype.Repository;
import com.internation.info.model.FileUpload;
@Repository
public class fileUploadVo extends FileUpload{
	private static final long serialVersionUID = 1L;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
