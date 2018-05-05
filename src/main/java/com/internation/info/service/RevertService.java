package com.internation.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.internation.info.dao.RevertMapper;
import com.internation.info.dao.ReviewMapper;
import com.internation.info.model.Revert;
import com.internation.info.model.RevertExample;
import com.internation.info.model.ReviewExample;

@Service
public class RevertService {
	@Autowired
	RevertMapper revertwMapper;
	@Autowired
	RevertExample revertExample;
	@Autowired
	Revert revert;
	
	//是否有当前评论的回复
	public List<Revert> findRevertByArticleIdAndFloor(int articleId,int floor){
		RevertExample revertE = new RevertExample();
		revertE.createCriteria().andArticleIdEqualTo(articleId).andReviewFloorEqualTo(floor);
		List<Revert> list = revertwMapper.selectByExample(revertE);
			return list;
	}
	
	//插入一条回复
	public int insertRevert(Revert rev){
		int result = revertwMapper.insert(rev);
		return result;
	}
	
}
