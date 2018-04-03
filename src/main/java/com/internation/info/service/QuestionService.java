package com.internation.info.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.AnswerMapper;
import com.internation.info.dao.QuestionMapper;
import com.internation.info.model.Answer;
import com.internation.info.model.AnswerExample;
import com.internation.info.model.Question;
import com.internation.info.model.QuestionExample;

@Service
public class QuestionService {
	@Autowired
	Question question;
	@Autowired
	QuestionMapper questionMapper;
	@Autowired
	QuestionExample questionExample;
	@Autowired
	Answer answer;
	@Autowired
	AnswerMapper anserMapper;
	@Autowired
	AnswerExample answerExample;

	public int addQuestion(Question ques) {
		int num = questionMapper.insert(ques);
		return num;
	}

	public List<Question> findMyQuestion(int userId) {
		questionExample.createCriteria().andQuestionerEqualTo(userId);
		List<Question> myQuestionList = questionMapper.selectByExample(questionExample);
		return myQuestionList;
	}

	// 查找问题详细
	public Question findQuestionDetailById(Integer questionId) {
		Question question = questionMapper.selectByPrimaryKey(questionId);
		return question;
	}

	// 查看问题的
	public List<Answer> findQuestionAnswerById(Integer questionId) {
		answerExample.createCriteria().andQuestionIdEqualTo(questionId);
		List<Answer> selectByExample = anserMapper.selectByExample(answerExample);
		return selectByExample;
	}
	
	//添加回答
	public int addAnswerFowQuestion(Answer answer){
		int num = anserMapper.insert(answer);
		return num;
	}
	
	public List<Answer> findAnswerByQuestionId(Integer questionId){
		answerExample.createCriteria().andQuestionIdEqualTo(questionId);
		List<Answer> selectByExampleList = anserMapper.selectByExample(answerExample);
		return selectByExampleList;
	}
	
	//删除问题
	public int deleteQuestionById(Integer id){
		int num = questionMapper.deleteByPrimaryKey(id);
		return num;
	}

	
	public Answer findAnswerById(int answerId){
		Answer answer = anserMapper.selectByPrimaryKey(answerId);
		return answer;
	}
	
	//更新    answer 表中的  采纳字段  值为  1   修改   问题表中的   采纳内容的  字段
	public int updateAnswerById(Answer answer){
		int ansNum = anserMapper.updateByPrimaryKey(answer);
		int answerId = answer.getId();
		Answer answer1 = anserMapper.selectByPrimaryKey(answerId);
		String content = answer1.getContent();
		int questionId = answer1.getQuestionId();
		Question question = questionMapper.selectByPrimaryKey(questionId);
		question.setAdoptTheContent(content);
		question.setIsresolve(1);
		int queNum = questionMapper.updateByPrimaryKeySelective(question);
		return queNum;
	}

	public int addReturnByFloor(int id,int floorId,String content){
		Answer answer2 = anserMapper.selectByPrimaryKey(id);
		answer.setAnswerTime(new Date());
		answer.setIsAnswer(0);
		answer.setContent(content);
		answer.setQuestionId(answer2.getQuestionId());
		answer.setuId(answer2.getuId());
		int num = anserMapper.insert(answer);
		return num;
	}


	public int updateQuestion(Question quest){
		int result = questionMapper.updateByPrimaryKeySelective(quest);
		return result;
	}
	
	public List<Question> findQuestionBySeeCount(){
		questionExample.setOrderByClause("seecount desc");
		List<Question> questionList = questionMapper.selectByExample(questionExample);
		return questionList;
	}
}