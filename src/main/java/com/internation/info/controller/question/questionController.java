package com.internation.info.controller.question;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internation.info.dao.QuestionMapper;
import com.internation.info.model.Question;
import com.internation.info.model.QuestionExample;
import com.internation.info.service.QuestionService;

@Controller
public class questionController {
	@Autowired
	QuestionService questionService;
	@Autowired
	Question question;
	//添加问题
	@RequestMapping("/addQuestion")
	public String addQuestion(Question que,HttpServletRequest req,Model model){
		question.setTitle(que.getTitle());
		question.setContent(que.getContent());
		question.setIsresolve(0);
		HttpSession session = req.getSession();
		question.setQuestioner((Integer)session.getAttribute("userId"));
		question.setQuestionTime(new Date());
		question.setSeeCount(0);
		int num = questionService.addQuestion(question);
		if(num>0){
			model.addAttribute("result", "发表成功");
		}else {
			model.addAttribute("result", "发表失败");
		}
		
		return "";
	}
}
