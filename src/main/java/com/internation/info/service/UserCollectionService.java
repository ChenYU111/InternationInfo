package com.internation.info.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.diff.myers.MyersDiff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.MyCollectionMapper;
import com.internation.info.model.MyCollection;
import com.internation.info.model.MyCollectionExample;
import com.internation.info.model.User;

@Service
public class UserCollectionService {
	@Autowired
	MyCollection myCollection;
	@Autowired
	MyCollectionMapper myCollectionMapper;
	@Autowired
	MyCollectionExample myCollectionExample;
	
	//我关注的专家
	public List<MyCollection> findMyCollectionList(Integer userId){
		List<MyCollection> myCollectionList = new ArrayList<MyCollection>();
		if(null!=userId&&!userId.equals("")){
			myCollectionExample.createCriteria().andUIdEqualTo(userId).andIsUserEqualTo(1);
			myCollectionList = myCollectionMapper.selectByExample(myCollectionExample);
		}
		return myCollectionList;
	}
	
	
		//查找   关注表中的   专家  人
		public MyCollection findCollectionUser(int professorId,int uId){
			MyCollectionExample myE = new MyCollectionExample();
			myE.createCriteria().andMyAttentionUserIdEqualTo(professorId).andIsUserEqualTo(uId);
			List<MyCollection> selectByExample = myCollectionMapper.selectByExample(myE);
			MyCollection mColl = new MyCollection();
			if(null!=selectByExample&&selectByExample.size()>0){
				mColl = selectByExample.get(0);
			}
			return mColl;
		}
		
		public int updateMyCollectionUser(MyCollection myCollection2){
			int result = myCollectionMapper.updateByPrimaryKeySelective(myCollection2);
			return result;
		}
		
		//当 表中么有 这个记录的时候   插入一条  关注专家
		public int insertProfessor(int professorId,int uId){
			MyCollection myCollection2 = findCollectionUser(professorId,uId);
			int result = 0;
			//当值为 
				myCollection.setIsUser(1);
				//关注的专家Id
				myCollection.setMyAttentionUserId(professorId);
				myCollection.setuId(uId);
				result = myCollectionMapper.insert(myCollection);
			
			return result;
		}
		//当表中   有这条记录   更新  isuser 为 1
		public int updateProfessorToAttention(int professorId,int uId){
			MyCollection myCollection2 = findCollectionUser(professorId,uId);
			int num = 0;
			if(myCollection2!=null&&!myCollection2.equals("")&&myCollection2.getIsUser()==0){
				myCollection2.setIsUser(1);
				num = updateMyCollectionUser(myCollection2);
			}
			return num;
		}
		
		
		//取消关注
		public int updateProfessorToNOAttention(int professorId,int uId){
			MyCollection myCollection2 = findCollectionUser(professorId,uId);
			int num = 0;
			if(myCollection2!=null&&!myCollection2.equals("")&&myCollection2.getIsUser()==1){
				myCollection2.setIsUser(0);
				num = updateMyCollectionUser(myCollection2);
			}
			return num;
		}
}
