package com.internation.info.controller.readInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internation.info.model.Article;
import com.internation.info.model.Review;
import com.internation.info.model.User;
import com.internation.info.service.InfoService;

@Controller
public class readInfoController {
	@Autowired
	InfoService infoService;
	@Autowired
	Review review;
	@RequestMapping("/infoList")
	public String Info(Model model){
		return "readInfo/readInfoTemplate";
	}
	//查看说有   没有类别的分类
	@RequestMapping("/readInfoAll")
	public String readInfoAll(Model model){
		List<Article> allArticleList = infoService.findAllArticle();
		model.addAttribute("allArticleList", allArticleList);
		return "readInfo/readInfoList";
	}
	
	
	//根据Id查看某一个文章的具体类容
	@RequestMapping("/readOneInfo/{id}")
	public String readOneInfo(@PathVariable("id")Integer articleId,HttpServletRequest req,Model model){
		Article article = infoService.findArticleByPrimaryKey(articleId);
		model.addAttribute("article", article);
		Integer num = article.getSeecount();
		if(num==null){
			num=0;
		}
		article.setSeecount(num+1);
		infoService.updateArticle(article);
		// 判断评论是否添加成功 如果添加成功 返回所用评论给 页面
		List<User> userList = new ArrayList<>();
		review.setArticle_id(articleId);
		List<Review> findReviewList = infoService.findReviewList(review.getArticle_id());
		if (findReviewList.size() > 0 && findReviewList != null) {
			model.addAttribute("reviewList", findReviewList);
			for (Review review : findReviewList) {
				User user = infoService.findUserNameList(review.getObserver_id());
				userList.add(user);
			}
			model.addAttribute("userList", userList);
		} else {
			model.addAttribute("review", "暂无评论");
		}
		HttpSession session = req.getSession();
		session.setAttribute("articleIdInDetail",articleId);
		return "readInfo/readInfoDetail";
	}
	
	@RequestMapping("/javaArticleList")
	public String JavaInfoList(Model model){
		List<Article> articleList = infoService.findAllArticle();
		List<Article> javaArticleList=new ArrayList<>();
		for (Article article : articleList) {
			if(article.getBlog_type().contains("Java")){
				javaArticleList.add(article);
			}
		}
		model.addAttribute("allArticleList", javaArticleList);
		return "readInfo/readInfoList";
	}
	
	
	@RequestMapping("/PhPArticleList")
	public String PhPInfoList(Model model){
		List<Article> articleList = infoService.findAllArticle();
		List<Article> javaArticleList=new ArrayList<>();
		for (Article article : articleList) {
			if(article.getBlog_type().contains("php")){
				javaArticleList.add(article);
			}
		}
		model.addAttribute("allArticleList", javaArticleList);
		return "readInfo/readInfoList";
	}
	
	
	@RequestMapping("/SQLArticleList")
	public String SQLInfoList(Model model){
		List<Article> articleList = infoService.findAllArticle();
		List<Article> javaArticleList=new ArrayList<>();
		for (Article article : articleList) {
			if(article.getBlog_type().contains("SQL")){
				javaArticleList.add(article);
			}
		}
		model.addAttribute("allArticleList", javaArticleList);
		return "readInfo/readInfoList";
	}
	
	
	@RequestMapping("/AIArticleList")
	public String AIInfoList(Model model){
		List<Article> articleList = infoService.findAllArticle();
		List<Article> javaArticleList=new ArrayList<>();
		for (Article article : articleList) {
			if(article.getBlog_type().contains("人工智能")){
				javaArticleList.add(article);
			}
		}
		model.addAttribute("allArticleList", javaArticleList);
		return "readInfo/readInfoList";
	}
}
