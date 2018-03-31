package com.internation.info.controller.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.ActionEnter;
import com.github.pagehelper.PageHelper;
import com.internation.info.common.PageBean;
import com.internation.info.dao.ArticleMapper;
import com.internation.info.model.Article;
import com.internation.info.model.ArticleExample;
import com.internation.info.model.ArticleExample.Criteria;
import com.internation.info.model.Review;
import com.internation.info.model.User;
import com.internation.info.service.InfoService;
import com.internation.info.service.UserService;

@Controller
public class InfoController {
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	ArticleExample articleExample;
	@Autowired
	InfoService infoService;
	@Autowired
	Article article;
	@Autowired
	Review review;
	@Autowired
	UserService userService;
	@RequestMapping("/writeInfo")
	public String addInfo() {
		return "info/writeInfo";
	}

	// 提交资讯
	@RequestMapping("/submitInfo")
	public String commitInfo(Article article, HttpServletRequest req, String submit) {
		HttpSession session = req.getSession();
		Article ar = new Article();
		StringBuffer title = new StringBuffer();
		title.append("<h1>" + article.getTitle() + "</h1>");
		ar.setTitle(title.toString());
		ar.setContent(article.getContent());
		if (submit.equals("发布")) {
			ar.setIspublish(1);// 发布
		} else if (submit.equals("保存到草稿箱")) {
			ar.setIspublish(0);// 提交到草稿箱
		}
		if (article.getIsprivate() != null) {
			ar.setIsprivate(article.getIsprivate());// 不是私有的
		}
		if (article.getOriginal() != null) {
			ar.setOriginal(article.getOriginal());// 是原创
		}
		ar.setCreateTime(new Date());
		ar.setBlog_type(article.getBlog_type());
		int uid = (int) session.getAttribute("userId");
		ar.setUid(uid);
		int result = articleMapper.insert(ar);
		if (result > 0) {
			System.err.println("插入返回的结果  " + result);
			articleExample.createCriteria().andTitleEqualTo(ar.getTitle()).andContentEqualTo(ar.getContent())
					.andCreateTimeEqualTo(ar.getCreateTime()).andBlog_typeEqualTo(ar.getBlog_type())
					.andUidEqualTo(ar.getUid());
			List<Article> articleList = articleMapper.selectByExample(articleExample);
			if (articleList != null && articleList.size() > 0) {
				session.setAttribute("article", articleList.get(0));
			}
			return "info/addSucArticle";
		} else {
			System.out.println("插入失败");
			return "info/writeInfo";
		}
	}

	// 查看文章 和 本文章的资讯   如果  有  评论   就  展示出来
	@RequestMapping("/seeOneArticle/{id}")
	public String seeOneArticle(@PathVariable("id") Integer articleId, HttpServletRequest req, Model model) {
		Article article = articleMapper.selectByPrimaryKey(articleId);
		model.addAttribute("article", article);
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
		return "info/seeArticleDetail";
	}

	// 添加评论并且查看评论
	@RequestMapping("/addreview")
	public String addReview(Review rev, HttpServletRequest req, Model model) {
		// 添加评论
		int articleId = (int) req.getSession().getAttribute("articleIdInDetail");
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
		int seeCount = infoService.findSeeCount(review.getArticle_id());
		review.setSeecount(seeCount + 1);
		int insertNum = infoService.insertReview(review);
		// 判断评论是否添加成功 如果添加成功 返回所用评论给 页面
		if (insertNum > 0) {
			List<Review> findReviewList = infoService.findReviewList(review.getArticle_id());
			if (findReviewList.size() > 0 && findReviewList != null) {
				model.addAttribute("reviewList", findReviewList);
			}
		}
		List<User> userList = new ArrayList<>();
		List<Review> findReviewList = infoService.findReviewList(articleId);
		for (Review review : findReviewList) {
			User user = infoService.findUserNameList(review.getObserver_id());
			userList.add(user);
		}
		model.addAttribute("userList", userList);
		return "info/seeArticleDetail";
	}

	@RequestMapping("/seeArticleList")
	public String articleList(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		articleExample.createCriteria().andUidEqualTo((int) session.getAttribute("userId"));
		articleExample.setOrderByClause("createTime desc");
		List<Article> articleList = articleMapper.selectByExample(articleExample);
		if (articleList != null && articleList.size() > 0) {
			model.addAttribute("articleList", articleList);
		} else {
			model.addAttribute("articleList", "您还没有写过文章，点击上面的 写资讯 来写一篇吧！");
		}
		return "info/articleManager";
	}

	@RequestMapping("/articleManager") // 这里有问题
	public String SearchArtocleList(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize, Article art, Model m) {
		article.setTitle(art.getTitle());
		if (art.getIspublish() == null) {
			art.setIspublish(1);
			article.setIspublish(art.getIspublish());
		}

		if (art.getOriginal() == null) {
			art.setOriginal(1);
			article.setOriginal(art.getOriginal());
		}
		article.setBlog_type(art.getBlog_type());
		if (pageSize == null) {
			pageSize = 5;
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		// PageBean<Article> pageBean = infoService.selectArticleLimit(pageNum,
		// pageSize,article,m);

		List<Article> articleList = infoService.selectArticleLimit(pageNum, pageSize, article, m);
		m.addAttribute("articles", articleList);
		return "info/articleManager";
		// pageData.getItems().
		// return pageData.getItems();
	}

	@RequestMapping(value = "/searchArticle", method = RequestMethod.POST)
	public String SearchArtocle(Article art, Model m) {
		article.setTitle(art.getTitle());
		if (art.getIspublish() == null) {
			art.setIspublish(1);
			article.setIspublish(art.getIspublish());
		}

		if (art.getOriginal() == null) {
			art.setOriginal(1);
			article.setOriginal(art.getOriginal());
		}
		article.setBlog_type(art.getBlog_type());
		// PageBean<Article> pageBean = infoService.selectArticleLimit(pageNum,
		// pageSize,article,m);
		List<Article> articleList = infoService.selectArticle(article, m);
		m.addAttribute("articles", articleList);
		return "info/articleManager";
		// pageData.getItems().
		// return pageData.getItems();
	}
	@PostMapping("/deleteArticleById/{id}")
	@ResponseBody
	public void deleteById(@PathVariable("id") Integer id){
		int result = infoService.deleteArticeById(id);
	}
}
