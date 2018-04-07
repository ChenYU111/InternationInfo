package com.internation.info.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.github.pagehelper.PageHelper;
import com.internation.info.common.PageBean;
import com.internation.info.dao.ArticleMapper;
import com.internation.info.dao.ReviewMapper;
import com.internation.info.dao.UserMapper;
import com.internation.info.model.Article;
import com.internation.info.model.ArticleExample;
import com.internation.info.model.ArticleExample.Criteria;
import com.internation.info.model.Review;
import com.internation.info.model.ReviewExample;
import com.internation.info.model.User;
import com.internation.info.model.UserExample;
@Service
public class InfoService {
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	ArticleExample articleExample;
	@Autowired
	PageBean<Article> pageBean;
	@Autowired
	ReviewMapper reviewMapper;
	@Autowired
	ReviewExample reviewExample;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserExample userExample;
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
	
	
	//根据文章的id来查找评论   找到最后一个评论的楼层
	public int findFloor(int articleId) {
		reviewExample.createCriteria().andArticle_idEqualTo(articleId);
		List<Review> reviewList = reviewMapper.selectByExample(reviewExample);
		int num = 0;
		//有评论
		if (reviewList != null && reviewList.size() > 0) {
			num = reviewList.size();
		}
		//有评论   取最后一条评论  的 楼层号 
		if (num > 0) {
			return reviewList.get(num - 1).getFloor_number();
		} else {
			//如果没有评论  返回1 
			return 0;
		}
	}
	//查看 评论被  查看了几次
	public int findSeeCount(int articleId){
		reviewExample.createCriteria().andArticle_idEqualTo(articleId);
		List<Review> reviewList = reviewMapper.selectByExample(reviewExample);
		int num = 0;
		if(reviewList!=null&&reviewList.size()>0){
			num = reviewList.size();
		}
		if(num>0){
		return reviewList.get(num-1).getSeecount();
		}else{
			return 0;
		}
	}
	
	
	public int insertReview(Review review){
		int num = reviewMapper.insert(review);
		return num;
	}
	//查出所用评论
	public List<Review> findReviewList(int articleId){
		//List<Review> reviewList = ArrayList<Review>();
		reviewExample.createCriteria().andArticle_idEqualTo(articleId);
		List<Review>reviewList = reviewMapper.selectByExample(reviewExample);
		return reviewList;
		
	}
	
	public User findUserNameList(int uId){
		userExample.createCriteria().andIdEqualTo(uId);
		User user = userMapper.selectByPrimaryKey(uId);
		return user;
	}
	
	
	public int deleteArticeById(Integer id){
		int num = articleMapper.deleteByPrimaryKey(id);
		return num;
	}
	
	
	public List<Article> findAllArticle(){
		articleExample.createCriteria().andIsprivateEqualTo(0);
		articleExample.createCriteria().andIspublishEqualTo(1);
		List<Article> allArticleList = articleMapper.selectByExample(articleExample);
		return allArticleList;
	}
	
	public Article findArticleByPrimaryKey(Integer articleId){
		Article article = articleMapper.selectByPrimaryKey(articleId);
		return article;
	}
	
	/*public List<Article> findArticleByType(String str){
		//articleExample.createCriteria().andBlog_typeBetween(str, );
		
		articleMapper.selectByExample(articleExample);
		return 
	}*/
	
	public int updateArticle(Article article){
		int num = articleMapper.updateByPrimaryKeySelective(article);
		return num;
	}
}
