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
import com.internation.info.model.Revert;
import com.internation.info.model.Review;
import com.internation.info.model.User;
import com.internation.info.service.InfoService;
import com.internation.info.service.RevertService;
import com.internation.info.service.UserService;
import com.internation.info.vo.articleVo;
import com.internation.info.vo.revertVo;
import com.internation.info.vo.reviewVo;

@Controller
public class readInfoController {
	@Autowired
	InfoService infoService;
	@Autowired
	Review review;
	@Autowired
	UserService userService;
	@Autowired
	RevertService revertService;
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
					reviewVo.setSeecount(review.getSeecount());
					reviewVo.setIsRevert(review.getIsRevert());
					if (null != review.getIsRevert() && !review.getIsRevert().equals("") && review.getIsRevert() == 1) {
						List<Revert> revertList = revertService.findRevertByArticleIdAndFloor(articleId,
								review.getFloor_number());
						if (!StringUtils.isEmpty(revertList)) {
							List<revertVo> revertVoList = new ArrayList<>();
							for (Revert revert2 : revertList) {
								revertVo revVo = new revertVo();
								User user3 = infoService.findUserNameList(revert2.getuId());
								revVo.setArticleId(revert2.getArticleId());
								revVo.setIsRevert(revert2.getIsRevert());
								revVo.setRevertCreateTime(revert2.getRevertCreateTime());
								revVo.setRevertFloor(revert2.getRevertFloor());
								revVo.setReviewFloor(revert2.getReviewFloor());
								revVo.setUsername(user3.getUserName());
								revVo.setuId(revert2.getuId());
								revVo.setRevert(revert2.getRevert());
								revertVoList.add(revVo);
							}
							reviewVo.setRevertList(revertVoList);
						}
					}
					reviewList.add(reviewVo);
				}
				model.addAttribute("reviewVoList", reviewList);
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
		
		// 判断评论是否添加成功 如果添加成功 返回所用评论给 页面
		if (insertNum > 0) {
			List<Review> findReviewList = infoService.findReviewList(review.getArticle_id());
			if (findReviewList.size() > 0 && findReviewList != null) {
				model.addAttribute("reviewList", findReviewList);
			}
		}
		List<reviewVo> reviewVoList = new ArrayList<>();
		List<Review> findReviewList = infoService.findReviewList(articleId);
		//根据文章id查询   文章  
		Article article2 = infoService.findArticleByPrimaryKey(articleId);
		int revertNum = 0;
		// 文章不等于null
		if (!StringUtils.isEmpty(article2)) {
			// 评论也不等于null
			if (!StringUtils.isEmpty(findReviewList)) {
				// 遍历评论
				for (Review review : findReviewList) {
					User user = infoService.findUserNameList(review.getObserver_id());
					reviewVo reviewVo = new reviewVo();
					reviewVo.setUsername(user.getUserName());
					reviewVo.setCreateTime(user.getCreateTime());
					reviewVo.setCreaTime(review.getCreateTime());
					reviewVo.setFloor_number(review.getFloor_number());
					reviewVo.setMessage(review.getMessage());
					reviewVo.setSeecount(review.getSeecount());
					reviewVo.setIsRevert(1);
						List<Revert> revertList = revertService.findRevertByArticleIdAndFloor(articleId,
								review.getFloor_number());
						if (!StringUtils.isEmpty(revertList)) {
							List<revertVo> revertVoList = new ArrayList<>();
							for (Revert revert2 : revertList) {
								revertVo revVo = new revertVo();
								User user3 = infoService.findUserNameList(revert2.getuId());
								revVo.setArticleId(revert2.getArticleId());
								revVo.setIsRevert(revert2.getIsRevert());
								revVo.setRevertCreateTime(revert2.getRevertCreateTime());
								User user5 = infoService.findUserNameList(revert2.getuId());				
								revVo.setUsername(user5.getUserName());
								revVo.setRevertFloor(revert2.getRevertFloor());
								revVo.setReviewFloor(revert2.getReviewFloor());
								revVo.setUsername(user3.getUserName());
								revVo.setuId(revert2.getuId());
								revVo.setRevert(revert2.getRevert());
								revertVoList.add(revVo);
							}
							reviewVo.setRevertList(revertVoList);
						}
						reviewVoList.add(reviewVo);
				}
			}
		}
		model.addAttribute("reviewVoList", reviewVoList);
		model.addAttribute("article", article2);
		return "readInfo/readInfoDetail";
	}
	
	@RequestMapping("/articleTop")
	public String findTop10Article(Model model){
		List<Article> list = infoService.findArticleBySeeCount();
		if(list!=null&&list.size()>0){
			int index=0;
			List<articleVo> articleVoList = new ArrayList<>();
			for (Article ar : list) {
				if(index<10){
					index++;
					articleVo articleVo= new articleVo();
					articleVo.setTitle(ar.getTitle());
					articleVo.setSeecount(ar.getSeecount());
					User user = userService.findUserByPKId(ar.getUid());
					articleVo.setUsername(user.getUserName());
					articleVo.setId(ar.getId());
					articleVoList.add(articleVo);
				}
			}
			model.addAttribute("articleList",articleVoList);
		}
		return "user/successMain";
	}
	
	
		// 给评论回复,文章id 从session中取，文章的楼层号从前台传过来
		@RequestMapping("/addReadInfoRevert")
		public String addRevert(int floor, HttpServletRequest req, Model model, String revert) {
			HttpSession session = req.getSession();
			int articleId = (int) session.getAttribute("articleId");
			//User user = infoService.findUserNameList(review.getObserver_id());
			//先添加一个   revert到数据库  
			Revert revert3 = new Revert();
			revert3.setIsRevert(1);
			revert3.setRevert(revert);
			revert3.setRevertCreateTime(new Date());
			revert3.setuId((int)session.getAttribute("userId"));
			revert3.setReviewFloor(floor);
			revert3.setArticleId(articleId);
			List<Revert> list = revertService.findRevertByArticleIdAndFloor(articleId, floor);
			int revertFloor = 0;
			if(list!=null&&list.size()>0){
				int index = list.size()-1;
				revertFloor = list.get(index).getRevertFloor()==null?0:list.get(index).getRevertFloor();
			}
			revert3.setRevertFloor(revertFloor+1);
			int revertResult = revertService.insertRevert(revert3);
			if(revertResult>0){
			List<Review> reviewList = infoService.findReviewByFloorAndArticleId(floor, articleId);
			if(reviewList!=null&&reviewList.size()>0){
				reviewList.get(0).setIsRevert(1);
				infoService.updateReview(reviewList.get(0));
			}
			}
			List<reviewVo> reviewVoList = new ArrayList<>();
			List<Review> findReviewList = infoService.findReviewList(articleId);
			Article article2 = infoService.findArticleByPrimaryKey(articleId);
			int revertNum = 0;
			// 文章不等于null
			if (!StringUtils.isEmpty(article2)) {
				// 评论也不等于null
				if (!StringUtils.isEmpty(findReviewList)) {
					// 遍历评论
					for (Review review : findReviewList) {
						User user = infoService.findUserNameList(review.getObserver_id());
						reviewVo reviewVo = new reviewVo();
						reviewVo.setUsername(user.getUserName());
						reviewVo.setCreateTime(user.getCreateTime());
						reviewVo.setCreaTime(review.getCreateTime());
						reviewVo.setFloor_number(review.getFloor_number());
						reviewVo.setMessage(review.getMessage());
						reviewVo.setSeecount(review.getSeecount());
						reviewVo.setIsRevert(1);
							List<Revert> revertList = revertService.findRevertByArticleIdAndFloor(articleId,
									review.getFloor_number());
							if (!StringUtils.isEmpty(revertList)) {
								List<revertVo> revertVoList = new ArrayList<>();
								for (Revert revert2 : revertList) {
									revertVo revVo = new revertVo();
									User user3 = infoService.findUserNameList(revert2.getuId());
									revVo.setArticleId(revert2.getArticleId());
									revVo.setIsRevert(revert2.getIsRevert());
									revVo.setRevertCreateTime(revert2.getRevertCreateTime());
									User user5 = infoService.findUserNameList(revert2.getuId());				
									revVo.setUsername(user5.getUserName());
									revVo.setRevertFloor(revert2.getRevertFloor());
									revVo.setReviewFloor(revert2.getReviewFloor());
									revVo.setUsername(user3.getUserName());
									revVo.setuId(revert2.getuId());
									revVo.setRevert(revert2.getRevert());
									revertVoList.add(revVo);
								}
								reviewVo.setRevertList(revertVoList);
							}
							reviewVoList.add(reviewVo);
					}
				}
			}
			model.addAttribute("reviewVoList", reviewVoList);
			model.addAttribute("article", article2);
			return "readInfo/readInfoDetail";

		}
}
