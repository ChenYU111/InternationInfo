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
import org.springframework.util.StringUtils;
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
import com.internation.info.model.MyCollection;
import com.internation.info.model.ArticleExample.Criteria;
import com.internation.info.model.Review;
import com.internation.info.model.User;
import com.internation.info.service.InfoService;
import com.internation.info.service.UserService;
import com.internation.info.vo.reviewVo;
import com.sun.org.apache.xpath.internal.operations.Mod;

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
	public String commitInfo(Article article, HttpServletRequest req, String submit,Model model) {
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
		System.out.println("主键值为===================="+result);
		if (result > 0) {
			System.err.println("插入返回的结果  " + result);
			articleExample.createCriteria().andTitleEqualTo(ar.getTitle()).andContentEqualTo(ar.getContent())
					.andCreateTimeEqualTo(ar.getCreateTime()).andBlog_typeEqualTo(ar.getBlog_type())
					.andUidEqualTo(ar.getUid());
			List<Article> articleList = articleMapper.selectByExample(articleExample);
			if (articleList != null && articleList.size() > 0) {
				session.setAttribute("article", articleList.get(0));
			}
			List<Article> artList =  articleMapper.selectByExample(articleExample);
			if(null!=artList&&artList.size()>0){
				int articleId = artList.get(artList.size()-1).getId();
				model.addAttribute("articleId", articleId);
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
					reviewList.add(reviewVo);
				}
				model.addAttribute("reviewList", reviewList);
			} else {
				model.addAttribute("review", "暂无评论");
			}
		}
		HttpSession session = req.getSession();
		session.setAttribute("articleId", articleId);
		return "info/seeArticleDetail";
	}

	// 添加评论并且查看评论
	@RequestMapping("/seeOneArticle/addreview")
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
			reVo.setSeecount(review.getSeecount());
			reviewVoList.add(reVo);
		}
		model.addAttribute("reviewList",reviewVoList);
		model.addAttribute("article",article2);
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
	public String SearchArticle(Article art, Model m) {
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
		if(articleList!=null&&!articleList.equals("")&&articleList.size()>0){
			m.addAttribute("articles", articleList);
		}
		return "info/articleManager";
		// pageData.getItems().
		// return pageData.getItems();
	}
	@PostMapping("/deleteArticleById/{id}")
	@ResponseBody
	public void deleteById(@PathVariable("id") Integer id){
		int result = infoService.deleteArticeById(id);
	}
	
	@RequestMapping("/myDrafts")
	public String myDrafts(HttpServletRequest req,Model model){
		int uId = (int) req.getSession().getAttribute("userId");
		List<Article> articleList = infoService.findMyDrafts(uId);
		if(articleList!=null&&!articleList.equals("")&&articleList.size()>0){
			model.addAttribute("articleList", articleList);
		}
		return "info/myDrafts";
	}
	
	
	//收藏/取消收藏文章
	@RequestMapping("/attentionArticle")
	public String attentionProfessor( HttpServletRequest req, Model model) {
		int articleId = (int) req.getSession().getAttribute("articleId");
		int uId = (int) req.getSession().getAttribute("userId");
		MyCollection myCollection = infoService.findCollectionArticle(articleId,uId);
		String result = "";
		/**
		 * 当表中没有这个专家的记录时 == 关注 当表中有这条记录(不为null),并且 记录中 isUser 的值为
		 * 0（已经取消），再点击一次应该只关注
		 **/
		// 当表中没有这个记录 insert
		if (StringUtils.isEmpty(myCollection)) {
			int num = infoService.insertArticle(articleId, uId);
			if (num > 0) {
				result = "收藏成功！";
			} else {
				result = "收藏失败！";
			}
			model.addAttribute("result", result);
			return "info/attentionArticleResult";
		}
		// 当表中有这个记录， 且 isUser 的值为 0（已经取消） ---- update 1
		if (myCollection!=null && !myCollection.equals("") && myCollection.getIsArticle()!=null&&!myCollection.getIsArticle().equals("")&&myCollection.getIsArticle() == 0) {
			int num1 = infoService.updateArticleToAttention(articleId, uId);
			if (num1 > 0) {
				result = "收藏成功！";
			} else {
				result = "收藏失败！";
			}
			model.addAttribute("result", result);
			return "info/attentionArticleResult";
		} else {
			// 当表中有这个记录， 且 isUser 的值为 1（已关注 ） ---- update 0
			int num2 = infoService.updateArticleToNOAttention(articleId, uId);
			if (num2> 0) {
				result = "取消收藏成功！";
			} else {
				result = "取消收藏失败！";
			}
			model.addAttribute("result", result);
			return "info/attentionArticleResult";
		}
	}
	
	@RequestMapping("/myattentionArticle")
	public String myAttentionArticle(HttpServletRequest req,Model model){
		List<MyCollection> list = infoService.findMyattentionArticle((int)req.getSession().getAttribute("userId"));
		List<Article> articleList = new ArrayList<>();
		if (!StringUtils.isEmpty(list)) {
			for (MyCollection myCollection : list) {
				int articleId = myCollection.getMyCollectionOnArticleId();
				Article aticle2 = articleMapper.selectByPrimaryKey(articleId);
				articleList.add(aticle2);
			}
		}
		model.addAttribute("articleList", articleList);
		return "info/myAttentionArticle";
	}
}
