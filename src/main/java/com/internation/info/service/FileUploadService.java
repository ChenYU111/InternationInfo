package com.internation.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.FileDownloadMapper;
import com.internation.info.dao.FileUploadMapper;
import com.internation.info.dao.UserMapper;
import com.internation.info.model.FileDownload;
import com.internation.info.model.FileDownloadExample;
import com.internation.info.model.FileUpload;
import com.internation.info.model.FileUploadExample;
import com.internation.info.model.User;
import com.internation.info.model.UserExample;

@Service
public class FileUploadService {
	@Autowired
	FileUploadMapper fileUploadMapper;
	@Autowired
	FileUploadExample fileUploadExample;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserExample userExample;
	@Autowired
	FileDownloadMapper fileDownloadMapper;
	@Autowired
	FileDownloadExample fileDownloadExample;
	@Autowired
	FileDownload fileDownload;
	public int  insertFileUpload(FileUpload fileuUpload){
		int insert = fileUploadMapper.insert(fileuUpload);
		return insert;
	}
	
	public List<FileUpload> findAllFileList(){
		List<FileUpload> fileAllList = fileUploadMapper.selectByExample(fileUploadExample);
		return fileAllList;
	}
	
	public User findUserById(int userId){
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}
	
	public FileUpload FileDetail(int fileId){
		FileUpload fileUpload = fileUploadMapper.selectByPrimaryKey(fileId);
		return fileUpload;
	}
	
	public void insertFileDown(FileDownload fDownload){
		
	}
}
