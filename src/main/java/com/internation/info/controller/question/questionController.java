package com.internation.info.controller.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.internation.info.common.SensitiveWord;
import com.internation.info.dao.QuestionMapper;
import com.internation.info.model.Answer;
import com.internation.info.model.Question;
import com.internation.info.model.QuestionExample;
import com.internation.info.model.QuestionRevert;
import com.internation.info.model.User;
import com.internation.info.service.QuestionService;
import com.internation.info.service.UserService;
import com.internation.info.vo.answerVo;
import com.internation.info.vo.questionRevertVo;

@Controller
public class questionController {
	@Autowired
	QuestionService questionService;
	@Autowired
	Question question;
	@Autowired
	Answer answer;
	@Autowired
	UserService userService;
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
		// 加载敏感词库
		SensitiveWord sw = new SensitiveWord("CensorWords.txt");
		sw.InitializationWork();
		que.setTitle(sw.filterInfo(que.getTitle()));
		que.setContent(sw.filterInfo(que.getContent()));
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
		List<Question> questionByQuestioner = questionService
				.findQuestionByQuestioner((Integer) session.getAttribute("userId"));
		int size = (questionByQuestioner != null && !questionByQuestioner.equals("") && questionByQuestioner.size() > 0)
				? questionByQuestioner.size() - 1 : 0;
		if (size > 0) {
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
	public String seeQuestionDetail(@PathVariable("id") Integer questionId, Model model, HttpServletRequest req) {
		Question findQuestionDetailById = questionService.findQuestionDetailById(questionId);
		int seecount = 0;
		if (findQuestionDetailById.getSeeCount() != null) {
			seecount = findQuestionDetailById.getSeeCount() + 1;
		}
		findQuestionDetailById.setSeeCount(seecount + 1);
		int result = questionService.updateQuestion(findQuestionDetailById);
		if (result > 0) {
			findQuestionDetailById = questionService.findQuestionDetailById(questionId);
		}
		model.addAttribute("questionDetail", findQuestionDetailById);
		List<Answer> findQuestionAnswerById = questionService.findQuestionAnswerById(questionId);
		List<answerVo> answerVoList = new ArrayList<>();
		if (findQuestionAnswerById != null && findQuestionAnswerById.size() > 0) {
			for (Answer answer : findQuestionAnswerById) {
				answerVo answerVo = new answerVo();
				answerVo.setId(answer.getId());
				answerVo.setContent(answer.getContent());
				answerVo.setAnswerTime(answer.getAnswerTime());
				answerVo.setFloor(answer.getFloor());
				answerVo.setIsAdopt(answer.getIsAdopt());
				answerVo.setQuestionId(answer.getQuestionId());
				User user = userService.findUserByPKId((int) req.getSession().getAttribute("userId"));
				answerVo.setUserName(user.getUserName());
				answerVo.setuId(user.getId());
				List<QuestionRevert> answerRevertList = questionService.findQuestionAnswerRevert(questionId,answer.getFloor());
				if (answerRevertList != null && answerRevertList.size() > 0) {
					List<questionRevertVo> questionRevertVoList = new ArrayList<>();
					for (QuestionRevert questionRevert2 : answerRevertList) {
						questionRevertVo qvo = new questionRevertVo();
						qvo.setId(questionRevert2.getId());
						qvo.setCreateTime(questionRevert2.getCreateTime());
						qvo.setQuestionFloor(questionRevert2.getQuestionFloor());
						qvo.setRevertFloor(questionRevert2.getRevertFloor());
						qvo.setRevertMessage(questionRevert2.getRevertMessage());
						User user2 = userService.findUserByPKId((int) req.getSession().getAttribute("userId"));
						qvo.setuId(user2.getId());
						qvo.setUsername(user2.getUserName());
						questionRevertVoList.add(qvo);
					}
					answerVo.setQuestionRevertVoList(questionRevertVoList);
				}
				answerVoList.add(answerVo);
			}
		}
		model.addAttribute("answerVoList", answerVoList);
		HttpSession session = req.getSession();
		session.setAttribute("seeQuestionId", questionId);
		return "question/seeQuestionDetail";
	}

	@RequestMapping("/addAnswer")
	public String addAnswer(Answer an, HttpServletRequest req, Model model) {
		// 加载敏感词库
		SensitiveWord sw = new SensitiveWord("CensorWords.txt");
		sw.InitializationWork();
		an.setContent(sw.filterInfo(an.getContent()));
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
			List<answerVo> answerVoList = new ArrayList<>();
			if (findQuestionAnswerById != null && findQuestionAnswerById.size() > 0) {
				for (Answer answer : findQuestionAnswerById) {
					answerVo answerVo = new answerVo();
					answerVo.setId(answer.getId());
					answerVo.setContent(answer.getContent());
					answerVo.setAnswerTime(answer.getAnswerTime());
					answerVo.setFloor(answer.getFloor());
					answerVo.setIsAdopt(answer.getIsAdopt());
					answerVo.setQuestionId(answer.getQuestionId());
					User user = userService.findUserByPKId((int) req.getSession().getAttribute("userId"));
					answerVo.setUserName(user.getUserName());
					answerVo.setuId(user.getId());
					List<QuestionRevert> answerRevertList = questionService.findQuestionAnswerRevert(questionId,answer.getFloor());
					if (answerRevertList != null && answerRevertList.size() > 0) {
						List<questionRevertVo> questionRevertVoList = new ArrayList<>();
						for (QuestionRevert questionRevert2 : answerRevertList) {
							questionRevertVo qvo = new questionRevertVo();
							qvo.setId(questionRevert2.getId());
							qvo.setCreateTime(questionRevert2.getCreateTime());
							qvo.setQuestionFloor(questionRevert2.getQuestionFloor());
							qvo.setRevertFloor(questionRevert2.getRevertFloor());
							qvo.setRevertMessage(questionRevert2.getRevertMessage());
							User user2 = userService.findUserByPKId((int) req.getSession().getAttribute("userId"));
							qvo.setuId(user2.getId());
							qvo.setUsername(user2.getUserName());
							questionRevertVoList.add(qvo);
						}
						answerVo.setQuestionRevertVoList(questionRevertVoList);
					}
					
					answerVoList.add(answerVo);
				}
			}
			model.addAttribute("answerVoList", answerVoList);
			return "question/seeQuestionDetail";
		} else {
			return "question/addAnswerF";
		}
	}

	@RequestMapping("/deleteQuestion/{id}")
	public void deleteQuestionById(@PathVariable("id") Integer questionId) {
		int num = questionService.deleteQuestionById(questionId);
	}

	@RequestMapping("/isAdoptAnswer/{id}")
	@ResponseBody
	public String isAdoptAnswer(@PathVariable("id") int questionId) {
		Answer answer = questionService.findAnswerById(questionId);
		String resultStr = "";
		if (null != answer.getIsAdopt() && !answer.getIsAdopt().equals("") && answer.getIsAdopt() != 1) {
			answer.setIsAdopt(1);
			int result = questionService.updateAnswerById(answer);
			if (result == 1) {
				resultStr = "采纳成功！";
			} else {
				resultStr = "采纳失败！";
			}
		} else {
			resultStr = "你已经采纳过此条答案！";
		}
		return resultStr;
	}

	@RequestMapping("/addReturnAnswer")
	public void isReturnAnswer(@PathVariable("id") Integer nswerId, Integer floorId, String content) {
		int result = questionService.addReturnByFloor(nswerId, floorId, content);
	}

	@RequestMapping("/orderBySeeCount")
	public String findQuestionBySeeCount(Model model) {
		List<Question> questionBySeeCountList = questionService.findQuestionBySeeCount();
		if (questionBySeeCountList != null && questionBySeeCountList.size() > 0) {
			model.addAttribute("questionList", questionBySeeCountList);
		}
		return "question/orderBySeeCount";
	}

	@RequestMapping("/toOrderByQuestionSeeCount")
	public String toOrderByQuestionSeeCount() {
		return "question/orderBySeeCount";
	}
	
	//添加回复并且返回信息
	@RequestMapping("/addAnswerRevert")
	public String addQuestionRevert(int answerfloor,String revertMessage,Model model,HttpServletRequest req){
		int questionId = (int) req.getSession().getAttribute("seeQuestionId");
		QuestionRevert qv = new QuestionRevert();
		qv.setCreateTime(new Date());
		qv.setQuestionId(questionId);
		qv.setQuestionFloor(answerfloor);
		qv.setRevertMessage(revertMessage);
		qv.setuId((int)req.getSession().getAttribute("userId"));
		QuestionRevert questionRevert = questionService.findQuestionAnswerRevertFloor(questionId, answerfloor);
		int revertFloor =0;
		if(questionRevert!=null&&!questionRevert.equals("")){
			revertFloor=questionRevert.getRevertFloor()==null?0:questionRevert.getRevertFloor();
		}
		qv.setRevertFloor(revertFloor+1);
		int result  = questionService.insert(qv);
		if (result > 0) {
			//得道答案的Id  改变  isanswer 
			// 查询所有 问题，回答，回复
			Question findQuestionDetailById = questionService.findQuestionDetailById(questionId);
			model.addAttribute("questionDetail", findQuestionDetailById);
			List<Answer> findQuestionAnswerById = questionService.findQuestionAnswerById(questionId);
			List<answerVo> answerVoList = new ArrayList<>();
			if (findQuestionAnswerById != null && findQuestionAnswerById.size() > 0) {
				for (Answer answer : findQuestionAnswerById) {
					answerVo answerVo = new answerVo();
					answerVo.setId(answer.getId());
					answerVo.setContent(answer.getContent());
					answerVo.setAnswerTime(answer.getAnswerTime());
					answerVo.setFloor(answer.getFloor());
					answerVo.setIsAdopt(answer.getIsAdopt());
					answerVo.setQuestionId(answer.getQuestionId());
					User user = userService.findUserByPKId((int) req.getSession().getAttribute("userId"));
					answerVo.setUserName(user.getUserName());
					answerVo.setuId(user.getId());
					List<QuestionRevert> answerRevertList = questionService.findQuestionAnswerRevert(questionId,answerfloor);
					if (answerRevertList != null && answerRevertList.size() > 0) {
						List<questionRevertVo> questionRevertVoList = new ArrayList<>();
						for (QuestionRevert questionRevert2 : answerRevertList) {
							questionRevertVo qvo = new questionRevertVo();
							qvo.setId(questionRevert2.getId());
							qvo.setCreateTime(questionRevert2.getCreateTime());
							qvo.setQuestionFloor(questionRevert2.getQuestionFloor());
							qvo.setRevertFloor(questionRevert2.getRevertFloor());
							qvo.setRevertMessage(questionRevert2.getRevertMessage());
							User user2 = userService.findUserByPKId((int) req.getSession().getAttribute("userId"));
							qvo.setuId(user2.getId());
							qvo.setUsername(user2.getUserName());
							questionRevertVoList.add(qvo);
						}
						answerVo.setQuestionRevertVoList(questionRevertVoList);
					}
					
					answerVoList.add(answerVo);
				}
			}
			model.addAttribute("answerVoList", answerVoList);
			return "question/seeQuestionDetail";
		} else {
			return "question/seeQuestionDetail";
		}
	}
}