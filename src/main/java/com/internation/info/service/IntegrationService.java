package com.internation.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.IntegrationMapper;
import com.internation.info.model.Integration;
import com.internation.info.model.IntegrationExample;
@Service
public class IntegrationService {
	@Autowired
	IntegrationMapper integrationMapper;
	@Autowired
	IntegrationExample integrationExample;
	public int insert(Integration integration){
		int result = integrationMapper.insert(integration);
		return result;
	}
	
	public int update(Integration integration){
		int result = integrationMapper.updateByPrimaryKey(integration);
		return result;
	}
	
	public List<Integration> findIntegrationByUId(int uId){
		IntegrationExample ie = new IntegrationExample();
		ie.createCriteria().andUserIdEqualTo(uId);
		List<Integration> list = integrationMapper.selectByExample(ie);
		return list;
	}
	
}
