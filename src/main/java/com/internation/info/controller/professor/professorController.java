package com.internation.info.controller.professor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.internation.info.controller.user.userController;
import com.internation.info.model.Article;
import com.internation.info.model.Integration;
import com.internation.info.model.MyCollection;
import com.internation.info.model.Question;
import com.internation.info.model.User;
import com.internation.info.service.InfoService;
import com.internation.info.service.QuestionService;
import com.internation.info.service.UserCollectionService;
import com.internation.info.service.professorService;
import com.internation.info.vo.professDetailVo;
import com.internation.info.vo.professVo;
import com.internation.info.vo.userDetailVo;
@Controller
public class professorController {
	@Autowired
	professorService professorService;
	@Autowired
	UserCollectionService userControllerService;
	@Autowired
	InfoService infoservice;
	@Autowired
	QuestionService questionService;
	@RequestMapping("/professorList")
	public String findAllProfessorList(Model model){
		List<User> professorList = professorService.findProfessorList();
		model.addAttribute("professorList", professorList);
		return "professor/professorList";
	}
	
	@RequestMapping("/application")
	public String appliciton(HttpServletRequest req,Model model){
		HttpSession session = req.getSession();
		int userId = (int) session.getAttribute("userId");
		model.addAttribute("userId", userId);
		return "professor/applicationProfessor";
	}
	
	
	@RequestMapping("/sureApplication/{id}")
	@ResponseBody
	public String applicationProfessor(@PathVariable("id") Integer userId, Model model) {
		User user = professorService.findUserByUserId(userId);
		String result = "";
		// 更改 提交审核 为 2 状态
		if (user.getIsprofessor() != 1) {
			user.setIsprofessor(2);
			int num = professorService.updateUser(user);
			if (num == 1) {
				result = "已经申请,等待管理员审核";
				boolean auditResult = professorService.auditProfessor(user.getId());
				if(auditResult==true){
					user.setIsprofessor(1);
					int auditNum = professorService.updateUser(user);
					if(auditNum>0){
						result="恭喜，审核通过！您已经成为网站的资讯专家！";
					}
				}
			} else {
				result = "申请失败";
			}
		} else {
			result="不可以申请，您已经是专家。";
		}
		model.addAttribute("result", result);
		return result;
	}
	
	//查看  专家   详情  展示  发表了多少文章，关注了多少
	//返回  发表了多少文章，多少积分，
	@RequestMapping("/seeProfessorDetail/{id}")
	public String seeProfessorDetail(@PathVariable("id") Integer userId,Model model){
		int integrationNum = 0;
		int articleCount=0;
		User user3 = professorService.findUserByUserId(userId);
		professDetailVo professDetailVo = new professDetailVo();
		professDetailVo.setUserName(user3.getUserName());
		int integrationCount = professorService.findIntegration(userId);
		integrationNum=integrationCount;
		List<Article> articleList = professorService.findPublishArticleCount(userId);
		if(null!=articleList&&articleList.size()>0){
			articleCount=articleList.size();
		}
		List<Integer> typeCount = new ArrayList<>();
		int javaCount=0;
		int sqlCount=0;
		int phpCount=0;
		for (Article article : articleList) {
			if(article.getBlog_type().equals("Java")){
				javaCount++;
			}
			if(article.getBlog_type().equals("php")){
				phpCount++;
			}
			if(article.getBlog_type().equals("sql")){
				sqlCount++;
			}
		}
		//比较大小，
		typeCount.add(javaCount);
		typeCount.add(phpCount);
		typeCount.add(sqlCount);
		
		professDetailVo.setIntegration(integrationCount);
		professDetailVo.setArticleCount(articleCount);
		model.addAttribute("professDetailVo", professDetailVo);
		List<Article> myArticleById = infoservice.findMyArticleById(userId);
		if(myArticleById!=null&&!myArticleById.equals("")){
			model.addAttribute("articleList", articleList);
		} else {
			model.addAttribute("articleList", "您还没有写过文章，点击上面的 写资讯 来写一篇吧！");
		}
		
		List<Question> findMyQuestion = questionService.findMyQuestion(userId);
		if (findMyQuestion != null && findMyQuestion.size() > 0) {
			model.addAttribute("myQuestionList", findMyQuestion);
		}
		return "professor/seeProfessorDetail";
	}
	
	@RequestMapping("/attentionProfessor/{id}")
	public String attentionProfessor(@PathVariable("id") Integer professorId, HttpServletRequest req, Model model) {
		int uId = (int) req.getSession().getAttribute("userId");
		MyCollection myCollection = userControllerService.findCollectionUser(professorId);
		String result = "";
		/**
		 * 当表中没有这个专家的记录时 == 关注 当表中有这条记录(不为null),并且 记录中 isUser 的值为
		 * 0（已经取消），再点击一次应该只关注
		 **/
		// 当表中没有这个记录 insert
		if (myCollection == null || myCollection.equals("")) {
			int num = userControllerService.insertProfessor(professorId, uId);
			if (num > 0) {
				result = "关注成功！";
			} else {
				result = "关注失败！";
			}
			model.addAttribute("result", result);
			return "professor/attentionProfessorResult";
		}
		// 当表中有这个记录， 且 isUser 的值为 0（已经取消） ---- update 1
		if (myCollection!=null && !myCollection.equals("") && myCollection.getIsUser() == 0) {
			int num1 = userControllerService.updateProfessorToAttention(professorId, uId);
			if (num1 > 0) {
				result = "关注成功！";
			} else {
				result = "关注失败！";
			}
			model.addAttribute("result", result);
			return "professor/attentionProfessorResult";
		} else {
			// 当表中有这个记录， 且 isUser 的值为 1（已关注 ） ---- update 0
			int num2 = userControllerService.updateProfessorToNOAttention(professorId, uId);
			if (num2> 0) {
				result = "取关成功！";
			} else {
				result = "取关失败！";
			}
			model.addAttribute("result", result);
			return "professor/attentionProfessorResult";
		}
	}

	//查看我的关注专家
	@RequestMapping("/myAttentionProfessor")
	public String myAttentionProfessorList(Model model,HttpServletRequest req){
		int userId = (int) req.getSession().getAttribute("userId");
		List<MyCollection> myCollectionList = userControllerService.findMyCollectionList(userId);
		List<User> userList = new ArrayList<>();
		if(null!=myCollectionList&&myCollectionList.size()>0){
			for (MyCollection myCollection : myCollectionList) {
				if(myCollection!=null&&!myCollection.equals("")){
					User user = professorService.findUserByUserId(myCollection.getMyAttentionUserId());
					userList.add(user);
				}
			}
		}
		model.addAttribute("userList", userList);
		return "professor/MyAttentionProfessorList";
	}
	
	//得到 top 5 专家   都是专家，根据积分来排序
	@RequestMapping("/top5Professor")
	public String getTop5Professor(Model model){
		List<User> professorList = professorService.findProfessor();
		List<Integration> integrationList = new ArrayList<>();
		if(professorList!=null&&professorList.size()>0){
			for (User user : professorList) {
				Integration integration = professorService.findIntegrationByUId(user.getId());
				//比较积分大小，得到积分前五的显示
				integrationList.add(integration);
			}
			Collections.sort(integrationList); 
		}
		List<userDetailVo> list = new ArrayList<>();
		if(integrationList.size()>5){
			int index = 0;
			for (Integration integration : integrationList) {
				if(index<5){
					User user = professorService.findUserByUserId(integration.getUserId());
					userDetailVo vo  = new userDetailVo();
					vo.setUserName(user.getUserName());
					vo.setId(user.getId());
					list.add(vo);
				}
				
			}
		}else{
			for (Integration integration : integrationList) {
					User user = professorService.findUserByUserId(integration.getUserId());
					userDetailVo vo  = new userDetailVo();
					vo.setUserName(user.getUserName());
					vo.setId(user.getId());
					list.add(vo);
		}
	}
	
		model.addAttribute("professVoList", list);
		return "user/successMain";
	}
	
	/**
	 * 自动审核是否能成为专家
	 * 积分超过 3000  发表 的文章超过  20篇
	 */
	
	
}
