package com.internation.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.GroupMemberMapper;
import com.internation.info.model.GroupMember;
import com.internation.info.model.GroupMemberExample;


@Service
public class GroupMemberService {
	@Autowired
	GroupMember groupMember;
	@Autowired
	GroupMemberMapper groupMemberMapper;
	@Autowired
	GroupMemberExample groupMemberExample;
	public List<GroupMember> list(int groupId){
		groupMemberExample.createCriteria().andGroupIdEqualTo(groupId);
		List<GroupMember> list = groupMemberMapper.selectByExample(groupMemberExample);
		return list;
	}
}
