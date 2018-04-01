package com.internation.info.service;

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
	
	//删除文章
	public int deleteQuestionById(Integer id){
		int num = questionMapper.deleteByPrimaryKey(id);
		return num;
	}
}
