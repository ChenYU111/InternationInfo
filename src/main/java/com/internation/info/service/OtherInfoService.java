package com.internation.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.OtherInfoMapper;
import com.internation.info.model.OtherInfo;
import com.internation.info.model.OtherInfoExample;
@Service
public class OtherInfoService {
	@Autowired
	OtherInfoMapper otherInfoMapper;
	@Autowired
	OtherInfoExample otherInfoExample;
	public List<OtherInfo>  findAllOtherInfo(){
		List<OtherInfo> list = otherInfoMapper.selectByExample(otherInfoExample);
		return list;
	}
	
	public OtherInfo findOtherInfo(int id){
		OtherInfo otherInfo = otherInfoMapper.selectByPrimaryKey(id);
		return otherInfo;
	}

	public List<OtherInfo> findOtherInfoTop10() {
		OtherInfoExample oe = new OtherInfoExample();
		oe.setOrderByClause("seecount desc");
		List<OtherInfo> list = otherInfoMapper.selectByExample(oe);
		return list;
	}
}
