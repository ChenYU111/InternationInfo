package com.internation.info.controller.readInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internation.info.model.Article;
import com.internation.info.model.Review;
import com.internation.info.model.User;
import com.internation.info.service.InfoService;
import com.internation.info.vo.reviewVo;

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
		if (!StringUtils.isEmpty(article)) {
			model.addAttribute("article", article);
			// 判断评论是否添加成功 如果添加成功 返回所用评论给 页面
			List<Review> findReviewList = infoService.findReviewList(articleId);
			List<reviewVo> reviewList = new ArrayList<>();
			if (findReviewList != null && findReviewList.size() > 0) {
				for (Review review : findReviewList) {
					User user = infoService.findUserNameList(review.getObserver_id());
					reviewVo reviewVo = new reviewVo();
					reviewVo.setUsername(user.getUserName());
					reviewVo.setCreateTime(review.getCreateTime());
					reviewVo.setFloor_number(review.getFloor_number());
					reviewVo.setMessage(review.getMessage());
					int seecount = review.getSeecount()==null?0:review.getSeecount();
					reviewVo.setSeecount(seecount);
					reviewList.add(reviewVo);
				}
				model.addAttribute("reviewList", reviewList);
			} else {
				model.addAttribute("review", "暂无评论");
			}
		}
		HttpSession session = req.getSession();
		session.setAttribute("articleId", articleId);
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
	
	
	
	@RequestMapping("/addreview")
	public String addReview(Review rev, HttpServletRequest req, Model model) {
		// 添加评论
		int articleId = (int) req.getSession().getAttribute("articleId");
		review.setArticle_title(rev.getArticle_title());
		HttpSession session = req.getSession();
		review.setObserver_id((Integer) session.getAttribute("userId"));
		review.setCreateTime(new Date());
		review.setArticle_id(articleId);
		review.setMessage(rev.getMessage());
		int num = 0;
		num = infoService.findFloor(review.getArticle_id());
		if(num>0){
			review.setFloor_number(num+1);
		}else if(num==0){
			review.setFloor_number(1);
		}
		review.setSeecount(0);
		int insertNum = infoService.insertReview(review);
		//根据文章id查询   文章  
		Article article2 = infoService.findArticleByPrimaryKey(articleId);
		// 判断评论是否添加成功 如果添加成功 返回所用评论给 页面
		if (insertNum > 0) {
			List<Review> findReviewList = infoService.findReviewList(review.getArticle_id());
			if (findReviewList.size() > 0 && findReviewList != null) {
				model.addAttribute("reviewList", findReviewList);
			}
		}
		List<Review> findReviewList = infoService.findReviewList(articleId);
		List<reviewVo> reviewVoList = new ArrayList<>();
		for (Review review : findReviewList) {
			User user = infoService.findUserNameList(review.getObserver_id());
			//将相关评论查询出来   放到   文章的   vo中
			reviewVo reVo = new reviewVo();
			reVo.setUsername(user.getUserName());
			reVo.setCreateTime(user.getCreateTime());
			reVo.setCreaTime(review.getCreateTime());
			reVo.setFloor_number(review.getFloor_number());
			reVo.setMessage(review.getMessage());
			int seecount = review.getSeecount()==null?0:review.getSeecount();
			reVo.setSeecount(seecount);
			reviewVoList.add(reVo);
			
		}
		model.addAttribute("reviewList",reviewVoList);
		model.addAttribute("article",article2);
		return "readInfo/readInfoDetail";
	}
}
