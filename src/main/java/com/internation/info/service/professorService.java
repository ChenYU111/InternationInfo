package com.internation.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.ArticleMapper;
import com.internation.info.dao.IntegrationMapper;
import com.internation.info.dao.MyCollectionMapper;
import com.internation.info.dao.UserMapper;
import com.internation.info.model.Article;
import com.internation.info.model.ArticleExample;
import com.internation.info.model.Integration;
import com.internation.info.model.IntegrationExample;
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
	@Autowired
	IntegrationMapper integrationMapper;
	@Autowired
	IntegrationExample integrationExample;
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	ArticleExample articleExample;
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
	
	//根据用户Id查积分
	public int findIntegration(int id){
		integrationExample.createCriteria().andUserIdEqualTo(id);
		List<Integration> userIntegrationlist = integrationMapper.selectByExample(integrationExample);
		int count = 0;
		if(null!=userIntegrationlist&&userIntegrationlist.size()>0){
			count = userIntegrationlist.get(0).getIntegration_number();
		}
		return count;
	}
	
	//查看   发表了多少 文章
	
	public List<Article> findPublishArticleCount(int uid){
		//是当前这个专家的，并且是非私密的
		articleExample.createCriteria().andUidEqualTo(uid).andIsprivateEqualTo(0);
		List<Article> articlelist = articleMapper.selectByExample(articleExample);
		return articlelist;
	}
	
	
	
}
