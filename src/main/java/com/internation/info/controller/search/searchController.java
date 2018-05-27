package com.internation.info.controller.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internation.info.model.Article;
import com.internation.info.model.Question;
import com.internation.info.model.User;
import com.internation.info.service.InfoService;
import com.internation.info.service.QuestionService;
import com.internation.info.service.UserService;
import com.internation.info.vo.searchVo;

@Controller
public class searchController {
	//可以搜索  资讯、专家、问题
	@Autowired
	InfoService infoService;
	@Autowired
	UserService userService;
	@Autowired
	QuestionService questionService;
	
	@RequestMapping("/mainSearch")
	public String search(String content,Model model){
		String str = content;
		List<Article> articleList = infoService.findArtcleByLikeTitieAndContent(str);
		List<User> userList = userService.findUserByLikeUsername(str);
		List<Question> questionByQuestioner = questionService.findQuestionByQuestioner(str);
		List<searchVo> searchVoList = new ArrayList<>();
		if(articleList!=null&&!articleList.equals("")){
			for (Article article : articleList) {
				searchVo searchVo = new searchVo();
				searchVo.setType("文章");
				searchVo.setTitle(article.getTitle());
				searchVo.setContent(article.getContent());
				User u = userService.findUserByPKId(article.getUid());
				searchVo.setUsername(u.getUserName());
				searchVo.setId(article.getId());
				searchVo.setSeeUrl("seeOneArticle");
				searchVo.setDeleteUrl("deleteArticleById");
				searchVoList.add(searchVo);
			}
		}
		
		if(questionByQuestioner!=null&&!questionByQuestioner.equals(""))
		for (Question question : questionByQuestioner) {
			searchVo searchVo = new searchVo();
			searchVo.setType("问答");
			searchVo.setTitle(question.getTitle());
			searchVo.setContent(question.getContent());
			User u = userService.findUserByPKId(question.getQuestioner());
			searchVo.setId(question.getId());
			searchVo.setUsername(u.getUserName());
			searchVo.setSeeUrl("seeQuestionDetail");
			searchVo.setDeleteUrl("deleteQuestion");
			searchVoList.add(searchVo);
		}
		
		if(userList!=null&&!userList.equals(""))
			for (User user : userList) {
				searchVo searchVo = new searchVo();
				searchVo.setType("用户");
				searchVo.setUsername(user.getUserName());
				searchVo.setId(user.getId());
				searchVo.setUsername(user.getUserName());
				searchVo.setSeeUrl("searchseeUserDetail");
				searchVoList.add(searchVo);
			}
		model.addAttribute("searchVoList", searchVoList);
		return "mainSearch/searchResult";
	}
}
