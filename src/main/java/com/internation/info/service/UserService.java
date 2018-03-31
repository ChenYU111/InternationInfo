package com.internation.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.UserMapper;
import com.internation.info.model.User;
import com.internation.info.model.UserExample;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserExample userExample;
	
	public String getUserName(int userId){
		User user = userMapper.selectByPrimaryKey(userId);
		return user.getUserName();
	}
}
