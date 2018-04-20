package com.internation.info.controller.question;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internation.info.dao.QuestionMapper;
import com.internation.info.model.Answer;
import com.internation.info.model.Question;
import com.internation.info.model.QuestionExample;
import com.internation.info.service.QuestionService;

@Controller
public class questionController {
	@Autowired
	QuestionService questionService;
	@Autowired
	Question question;
	@Autowired
	Answer answer;

	@RequestMapping("toQuestion")
	public String toQuestion() {
		return "question/questionTemplate";
	}

	@RequestMapping("/addQ")
	public String addQ() {
		return "question/addQuestion";
	}

	// 添加问题
	@RequestMapping("/addQuestion")
	public String addQuestion(Question que, HttpServletRequest req, Model model) {
		question.setTitle(que.getTitle());
		question.setContent(que.getContent());
		question.setIsresolve(0);
		HttpSession session = req.getSession();
		question.setQuestioner((Integer) session.getAttribute("userId"));
		question.setQuestionTime(new Date());
		question.setSeeCount(0);
		question.setProfessorName(que.getProfessorName());
		int num = questionService.addQuestion(question);
		if (num > 0) {
			model.addAttribute("result", "发表成功");
		} else {
			model.addAttribute("result", "发表失败");
		}
		List<Question> questionByQuestioner = questionService.findQuestionByQuestioner((Integer) session.getAttribute("userId"));
		int size = (questionByQuestioner!=null&&!questionByQuestioner.equals("")&&questionByQuestioner.size()>0)?questionByQuestioner.size()-1:0;
		if(size>0){
			int qId = questionByQuestioner.get(size).getId();
			model.addAttribute("qId", qId);
		}
		return "question/addSucQuestion";
	}

	@RequestMapping("/myQuestion")
	public String MyQuestionList(HttpServletRequest req, Model model) {
		int userId = (int) req.getSession().getAttribute("userId");
		List<Question> findMyQuestion = questionService.findMyQuestion(userId);
		if (findMyQuestion != null && findMyQuestion.size() > 0) {
			model.addAttribute("myQuestionList", findMyQuestion);
		}
		return "question/myQuestion";
	}

	@RequestMapping("/seeQuestionDetail/{id}")
	public String seeQuestionDetail(@PathVariable("id") Integer questionId, Model model,HttpServletRequest req) {
		Question findQuestionDetailById = questionService.findQuestionDetailById(questionId);
		int seecount = 0;
		if(findQuestionDetailById.getSeeCount()!=null){
			seecount=findQuestionDetailById.getSeeCount()+1;
		}
		findQuestionDetailById.setSeeCount(seecount+1);
		int result = questionService.updateQuestion(findQuestionDetailById);
		if(result>0){
			findQuestionDetailById = questionService.findQuestionDetailById(questionId);
		}
		model.addAttribute("questionDetail", findQuestionDetailById);
		List<Answer> findQuestionAnswerById = questionService.findQuestionAnswerById(questionId);
		if (findQuestionAnswerById != null && findQuestionAnswerById.size() > 0) {
			model.addAttribute("answerList", findQuestionAnswerById);
		}
		HttpSession session = req.getSession();
		session.setAttribute("seeQuestionId", questionId);
		return "question/seeQuestionDetail";
	}
	
	@RequestMapping("/addAnswer")
	public String addAnswer( Answer an, HttpServletRequest req, Model model) {
		int questionId = (int) req.getSession().getAttribute("seeQuestionId");
		answer.setuId((int) req.getSession().getAttribute("userId"));
		List<Answer> findAnswerByQuestionId = questionService.findAnswerByQuestionId(questionId);
		int f = 0;
		int num = 0;
		if (findAnswerByQuestionId != null && findAnswerByQuestionId.size() > 0) {
			num = findAnswerByQuestionId.size();
		}
		if (num > 0) {
			answer.setFloor(findAnswerByQuestionId.get(num - 1).getFloor() + 1);
		} else {
			answer.setFloor(1);
		}
		answer.setContent(an.getContent());
		answer.setIsAdopt(0);
		answer.setQuestionId(questionId);
		answer.setAnswerTime(new Date());
		int count = questionService.addAnswerFowQuestion(answer);
		if (count > 0) {
			Question findQuestionDetailById = questionService.findQuestionDetailById(questionId);
			model.addAttribute("questionDetail", findQuestionDetailById);
			List<Answer> findQuestionAnswerById = questionService.findQuestionAnswerById(questionId);
			if (findQuestionAnswerById != null && findQuestionAnswerById.size() > 0) {
				model.addAttribute("answerList", findQuestionAnswerById);
			}
			return "question/seeQuestionDetail";
		} else {
			return "question/addAnswerF";
		}
	}
	@RequestMapping("/deleteQuestion/{id}")
	public void deleteQuestionById(@PathVariable("id") Integer questionId){
		int num = questionService.deleteQuestionById(questionId);
	}
	
	@RequestMapping("/isAdoptAnswer/{id}")
	public String  isAdoptAnswer(@PathVariable("id") int questionId){
		Answer answer = questionService.findAnswerById(questionId);
		answer.setIsAdopt(1);
		int result = questionService.updateAnswerById(answer);
		return "question/seeQuestionDetail";
	}
	
	@RequestMapping("/addReturnAnswer")
	public void  isReturnAnswer(@PathVariable("id") Integer nswerId,Integer floorId , String content){
		int result = questionService.addReturnByFloor(nswerId, floorId, content);
	}
	
	@RequestMapping("/orderBySeeCount")
	public String findQuestionBySeeCount(Model model){
		List<Question> questionBySeeCountList = questionService.findQuestionBySeeCount();
		if(questionBySeeCountList!=null&&questionBySeeCountList.size()>0){
			model.addAttribute("questionList", questionBySeeCountList);
		}
		return "question/orderBySeeCount";
	}
	
	@RequestMapping("/toOrderByQuestionSeeCount")
	public String toOrderByQuestionSeeCount(){
		return "question/orderBySeeCount";
	}
	
	
}