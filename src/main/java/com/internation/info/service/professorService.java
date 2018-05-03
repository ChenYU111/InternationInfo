package com.internation.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
		UserExample uExample = new UserExample();
		uExample.createCriteria().andIsprofessorEqualTo(1);
		List<User> ProfessorList = userMapper.selectByExample(uExample);
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
		IntegrationExample intExample = new IntegrationExample();
		intExample.createCriteria().andUserIdEqualTo(id);
		List<Integration> userIntegrationlist = integrationMapper.selectByExample(intExample);
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

	public List<User> findProfessor() {
		// TODO Auto-generated method stub
		UserExample uExample = new UserExample();
		uExample.createCriteria().andIsprofessorEqualTo(1);
		List<User> list = userMapper.selectByExample(uExample);
		return list;
	}
	
	//根据用户id查找积分
	public Integration findIntegrationByUId(Integer uId){
		IntegrationExample integratExample = new IntegrationExample();
		integratExample.createCriteria().andUserIdEqualTo(uId);
		List<Integration> list = integrationMapper.selectByExample(integratExample);
		Integration integration=new Integration();
		if(list!=null&&list.size()>0){
			integration =  list.get(0);
		}
		return integration;
	}
	
	//查找  一个人发布了原创文章多少个
	public List<Article> findArticleByUid(Integer uId){
		articleExample.createCriteria().andUidEqualTo(uId).andIspublishEqualTo(1).andOriginalEqualTo(1);
		List<Article> list = articleMapper.selectByExample(articleExample);
		return list;
	}
	
	/**
	 * 自动审核是否能成为专家
	 * 积分超过 3000  发表 的文章超过  20篇
	 */
	public boolean auditProfessor(Integer uId){
		Integration integration = findIntegrationByUId(uId);
		if(!StringUtils.isEmpty(integration)){
			List<Article> list=findArticleByUid(uId);
			int articleNum = list!=null&&list.size()>0?list.size():0;
			if(integration.getIntegration_number()>=3000&&articleNum>=20){
				return true;
			}else {
				return false;
			}
		}else{
			return false;
		}
	}
	
}
