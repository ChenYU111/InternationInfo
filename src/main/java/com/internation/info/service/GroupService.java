package com.internation.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.GroupMapper;
import com.internation.info.model.Group;
import com.internation.info.model.GroupExample;

@Service
public class GroupService {
	@Autowired
	GroupMapper groupMapper;
	@Autowired
	GroupExample groupExample;
	@Autowired
	Group group;
	public  List<Group> list(Group g){
		groupExample.createCriteria().andIdEqualTo(g.getId());
		List<Group> list = groupMapper.selectByExample(groupExample);
		return list;
	}
	
	public Group selectByPrimaryKey(int id){
		Group g = groupMapper.selectByPrimaryKey(id);
		return g;
	}
}
