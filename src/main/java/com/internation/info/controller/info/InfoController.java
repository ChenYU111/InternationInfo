package com.internation.info.controller.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import com.baidu.ueditor.ActionEnter;
import com.internation.info.dao.ArticleMapper;
import com.internation.info.model.Article;
import com.internation.info.model.ArticleExample;
import com.internation.info.model.User;
 
	
@Controller
public class InfoController {
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	ArticleExample articleExample;
	@RequestMapping("/writeInfo")
	public String addInfo(){
		return "info/writeInfo";
	}
	
	//提交资讯
	@RequestMapping("/submitInfo")
	public String commitInfo(Article article,HttpServletRequest req,String submit){
		HttpSession session = req.getSession();
		Article ar = new Article();
		StringBuffer title= new StringBuffer();
		title.append("<h1>"+article.getTitle()+"</h1>");
		ar.setTitle(title.toString());
		ar.setContent(article.getContent());
		if(submit.equals("发布")){
			ar.setIspublish(1);//发布
		}else if(submit.equals("保存到草稿箱")){
			ar.setIspublish(0);//提交到草稿箱
		}
		ar.setIsprivate(article.getIsprivate());//不是私有的
		ar.setOriginal(article.getOriginal());//是原创
		ar.setCreateTime(new Date());
		ar.setBlog_type(article.getBlog_type());
		int uid =(int) session.getAttribute("userId");
		ar.setUid(uid);
		int result = articleMapper.insert(ar);
		if(result>0){
			System.err.println("插入返回的结果  "+result);
			articleExample.createCriteria().andTitleEqualTo(ar.getTitle()).andContentEqualTo(ar.getContent()).andCreateTimeEqualTo(ar.getCreateTime()).andBlog_typeEqualTo(ar.getBlog_type()).andUidEqualTo(ar.getUid());
			List<Article> articleList = articleMapper.selectByExample(articleExample);
			if(articleList!=null&&articleList.size()>0){
				int aid = articleList.get(0).getId();
				session.setAttribute("articleId", aid);
			}
			return "info/addSucArticle";
		}else{
			System.out.println("插入失败");
			return "info/writeInfo";
		}
	}
	
	@RequestMapping("/seeOneArticle")
	public String seeOneArticle(HttpServletRequest req,Model model){
		HttpSession session = req.getSession();
		int articleId = (int)session.getAttribute("articleId");
		Article article = articleMapper.selectByPrimaryKey(articleId);
		model.addAttribute("article",article);
		return "info/seeArticleDetail";
	}
	
	/*@RequestMapping("/seeOneArticleFromDraft")
	public String seeOneArticleFromDraft(HttpServletRequest req,Model model){
		HttpSession session = req.getSession();
		int articleId = (int)session.getAttribute("articleId");
		Article article = articleMapper.selectByPrimaryKey(articleId);
		model.addAttribute("article",article);
		return "info/seeOneArticleDetailFromDraft";
	}*/
}
