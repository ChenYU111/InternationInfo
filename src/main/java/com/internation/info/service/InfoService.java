package com.internation.info.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.github.pagehelper.PageHelper;
import com.internation.info.common.PageBean;
import com.internation.info.dao.ArticleMapper;
import com.internation.info.model.Article;
import com.internation.info.model.ArticleExample;
import com.internation.info.model.ArticleExample.Criteria;
@Service
public class InfoService {
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	ArticleExample articleExample;
	@Autowired
	PageBean<Article> pageBean;
	/*public PageBean<Article> selectArticleLimit(Integer pageNum, Integer pageSize, Article article,Model m){*/
	public List<Article> selectArticleLimit(Integer pageNum, Integer pageSize, Article article,Model m){
	
			
		Criteria criteria = articleExample.createCriteria();
		if (article.getTitle()!= null && article.getTitle().trim().length() > 0) {
			criteria.andTitleLike("%" + article.getTitle() + "%");
		}
		/*if (content != null && content.trim().length() > 0) {
			criteria.andContentLike("%" + content + "%");
		}*/
		if (article.getBlog_type()!= null && article.getBlog_type().trim().length() > 0) {
			criteria.andBlog_typeLike("%" + article.getBlog_type() + "%");
		}
		if(article.getOriginal()==null){
			article.setOriginal(1);
		}
		criteria.andOriginalEqualTo(article.getOriginal()).andIspublishEqualTo(article.getIspublish());
		if (pageSize == null) {
			pageSize = 5;
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		 List<Article> limitArticleList = articleMapper.selectByExample(articleExample);
		 m.addAttribute("limitArticleList",limitArticleList);
		
		 pageBean.setItems(limitArticleList);
		 
		 //return pageBean;
		 return limitArticleList;
	}
	public List<Article> selectArticle(Article article,Model m){
		Criteria criteria = articleExample.createCriteria();
		if (article.getTitle()!= null && article.getTitle().trim().length() > 0) {
			criteria.andTitleLike("%" + article.getTitle() + "%");
		}
		/*if (content != null && content.trim().length() > 0) {
			criteria.andContentLike("%" + content + "%");
		}*/
		if (article.getBlog_type()!= null && article.getBlog_type().trim().length() > 0) {
			criteria.andBlog_typeLike("%" + article.getBlog_type() + "%");
		}
		if(article.getOriginal()==null){
			article.setOriginal(1);
		}
		criteria.andOriginalEqualTo(article.getOriginal()).andIspublishEqualTo(article.getIspublish());
//		if (pageSize == null) {
//			pageSize = 5;
//		}
//		if (pageNum == null) {
//			pageNum = 1;
//		}
		
		//RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		//PageHelper.startPage(pageNum, pageSize);
		 List<Article> limitArticleList = articleMapper.selectByExample(articleExample);
		//json.put("total", limitArticleList.size());
		//json.put("rows", limitArticleList);
		//model.addAttribute("json", json);
		 //当时知道数据类型时，可以进行强转。String s=JSON.toJSONString(json);JSONObject json=JSONObject.parentObject(String s)
		// String jsonStr= json.toJSONString(limitArticleList); 
		 m.addAttribute("limitArticleList",limitArticleList);
		
		return limitArticleList;
	}
}
