package com.internation.info.service;

import java.util.List;

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

	public List<User> findUserByLikeUsername(String str) {
		String string = "%"+str+"%";
		userExample.createCriteria().andUserNameLike(string);
		List<User> userList = userMapper.selectByExample(userExample);
		return userList;
	}
	
	public User findUserByPKId(Integer uId){
		User user = userMapper.selectByPrimaryKey(uId);
		return user;
	}
	public int update(User user){
		int result = userMapper.updateByPrimaryKey(user);
		return result;
	}

	public List<User> findAllUser() {
		List<User> list = userMapper.selectByExample(userExample);
		return list;
		
	}

	public List<User> findUserByUN(String userName) {
		// TODO Auto-generated method stub
		UserExample ue = new UserExample();
		ue.createCriteria().andUserNameEqualTo(userName);
		List<User>  list= userMapper.selectByExample(ue);
		return list;
	}
	
}
