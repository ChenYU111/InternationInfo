package com.internation.info.well;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.BasicMessageWallMapper;
import com.internation.info.model.BasicMessageWall;
import com.internation.info.model.BasicMessageWallExample;
@Service
public class BasicMessageWallService  {

    /**
     * 获取对应数量的留言列表
     * @param num
     * @return
     */
	@Autowired
	BasicMessageWallMapper basicMessWallMapper;
	@Autowired
	BasicMessageWallExample basicMessageWallExample;
	//根据Id 
    public BasicMessageWall getMessage(int id){
    	BasicMessageWall messageWall = basicMessWallMapper.selectByPrimaryKey(id);
    	return messageWall;
    }
    
    public List<BasicMessageWall> getMessageList(int num){
    	List<BasicMessageWall> list = basicMessWallMapper.selectByExample(basicMessageWallExample);
    	return list;
    }

	public int save(BasicMessageWall msg) {
		// TODO Auto-generated method stub
		int result = basicMessWallMapper.insert(msg);
		return result;
	}
}
