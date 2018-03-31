package com.internation.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internation.info.dao.QuestionMapper;
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
	
	public int addQuestion(Question ques){
		int num = questionMapper.insert(question);
		return 0;
	}
		
}
