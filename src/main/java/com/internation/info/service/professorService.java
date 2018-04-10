package com.internation.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.UserMapper;
import com.internation.info.model.User;
import com.internation.info.model.UserExample;

@Service
public class professorService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserExample userExample;
	@Autowired
	User user;
	public List<User> findProfessorList(){
		userExample.createCriteria().andIsprofessorEqualTo(1);
		List<User> ProfessorList = userMapper.selectByExample(userExample);
		return ProfessorList;
	}
	
	public User findUserByUserId(Integer id){
		User user2 = userMapper.selectByPrimaryKey(id);
		return user2;
	}
	
	public int updateUser(User user){
		int num = userMapper.updateByPrimaryKeySelective(user);
		return num;
	}
}
