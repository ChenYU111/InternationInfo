package com.internation.info.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.parser.SqlServer;
import com.internation.info.common.PageBean;
import com.internation.info.dao.ArticleMapper;
import com.internation.info.dao.MyCollectionMapper;
import com.internation.info.dao.ReviewMapper;
import com.internation.info.dao.UserMapper;
import com.internation.info.model.Article;
import com.internation.info.model.ArticleExample;
import com.internation.info.model.MyCollection;
import com.internation.info.model.MyCollectionExample;
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
	@Autowired
	MyCollectionMapper myCollectionMapper;
	@Autowired
	MyCollectionExample myCollectionExample;
	@Autowired
	MyCollection myCollection;
	@Autowired
	SqlSession sqlSession;
	@Autowired
	Review review;
	
	/*
	 * public PageBean<Article> selectArticleLimit(Integer pageNum, Integer
	 * pageSize, Article article,Model m){
	 */
	public List<Article> selectArticleLimit(Integer pageNum, Integer pageSize, Article article, Model m) {

		Criteria criteria = articleExample.createCriteria();
		if (article.getTitle() != null && article.getTitle().trim().length() > 0) {
			criteria.andTitleLike("%" + article.getTitle() + "%");
		}
		/*
		 * if (content != null && content.trim().length() > 0) {
		 * criteria.andContentLike("%" + content + "%"); }
		 */
		if (article.getBlog_type() != null && article.getBlog_type().trim().length() > 0) {
			criteria.andBlog_typeLike("%" + article.getBlog_type() + "%");
		}
		if (article.getOriginal() == null) {
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
		m.addAttribute("limitArticleList", limitArticleList);

		pageBean.setItems(limitArticleList);

		// return pageBean;
		return limitArticleList;
	}

	public List<Article> selectArticle(Article article, Model m) {
		Criteria criteria = articleExample.createCriteria();
		if (article.getTitle() != null && article.getTitle().trim().length() > 0) {
			criteria.andTitleLike("%" + article.getTitle() + "%");
		}
		/*
		 * if (content != null && content.trim().length() > 0) {
		 * criteria.andContentLike("%" + content + "%"); }
		 */
		if (article.getBlog_type() != null && article.getBlog_type().trim().length() > 0) {
			criteria.andBlog_typeLike("%" + article.getBlog_type() + "%");
		}
		if (article.getOriginal() == null) {
			article.setOriginal(1);
		}
		criteria.andOriginalEqualTo(article.getOriginal()).andIspublishEqualTo(article.getIspublish());
		// if (pageSize == null) {
		// pageSize = 5;
		// }
		// if (pageNum == null) {
		// pageNum = 1;
		// }

		// RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		// PageHelper.startPage(pageNum, pageSize);
		List<Article> limitArticleList = articleMapper.selectByExample(articleExample);
		// json.put("total", limitArticleList.size());
		// json.put("rows", limitArticleList);
		// model.addAttribute("json", json);
		// 当时知道数据类型时，可以进行强转。String s=JSON.toJSONString(json);JSONObject
		// json=JSONObject.parentObject(String s)
		// String jsonStr= json.toJSONString(limitArticleList);
		m.addAttribute("limitArticleList", limitArticleList);

		return limitArticleList;
	}

	// 根据文章的id来查找评论 找到最后一个评论的楼层
	public int findFloor(int articleId) {
		ReviewExample revE = new ReviewExample();
		revE.createCriteria().andArticle_idEqualTo(articleId);
		List<Review> reviewList = reviewMapper.selectByExample(revE);
		int num = 0;
		// 有评论
		if (reviewList != null && reviewList.size() > 0) {
			num = reviewList.size();
		}
		// 有评论 取最后一条评论 的 楼层号
		if (num > 0) {
			return reviewList.get(num - 1).getFloor_number();
		} else {
			// 如果没有评论 返回1
			return 0;
		}
	}

	// 查看 评论被 查看了几次
	public int findSeeCount(int articleId) {
		reviewExample.createCriteria().andArticle_idEqualTo(articleId);
		List<Review> reviewList = reviewMapper.selectByExample(reviewExample);
		int num = 0;
		if (reviewList != null && reviewList.size() > 0) {
			num = reviewList.size();
		}
		if (num > 0) {
			return reviewList.get(num - 1).getSeecount();
		} else {
			return 0;
		}
	}

	public int insertReview(Review review) {
		int num = reviewMapper.insert(review);
		return num;
	}

	// 查出所用评论
	public List<Review> findReviewList(int articleId) {
		ReviewExample reE = new ReviewExample();
		reE.createCriteria().andArticle_idEqualTo(articleId);
		List<Review> reviewList = reviewMapper.selectByExample(reE);
		return reviewList;

	}

	public User findUserNameList(int uId) {
		userExample.createCriteria().andIdEqualTo(uId);
		User user = userMapper.selectByPrimaryKey(uId);
		return user;
	}

	public int deleteArticeById(Integer id) {
		int num = articleMapper.deleteByPrimaryKey(id);
		return num;
	}

	// 查看所有发布的文章
	public List<Article> findAllArticle() {
		ArticleExample ae = new ArticleExample();
		ae.createCriteria().andIsprivateEqualTo(0).andIspublishEqualTo(1).andIdentifyingCodeEqualTo("1");
		List<Article> allArticleList = articleMapper.selectByExample(ae);
		return allArticleList;
	}

	public Article findArticleByPrimaryKey(Integer articleId) {
		Article article = articleMapper.selectByPrimaryKey(articleId);
		return article;
	}

	/*
	 * public List<Article> findArticleByType(String str){
	 * //articleExample.createCriteria().andBlog_typeBetween(str, );
	 * 
	 * articleMapper.selectByExample(articleExample); return }
	 */

	public int updateArticle(Article article) {
		int num = articleMapper.updateByPrimaryKeySelective(article);
		return num;
	}

	public List<Article> findMyArticleById(Integer uId) {
		ArticleExample aExample = new ArticleExample();
		aExample.createCriteria().andUidEqualTo(uId).andIdentifyingCodeEqualTo("1");
		List<Article> myArticleList = articleMapper.selectByExample(aExample);
		return myArticleList;
	}

	public List<Article> findArtcleByLikeTitieAndContent(String str) {
		String string = "%" + str + "%";
		articleExample.createCriteria().andTitleLike(string);
		List<Article> list = articleMapper.selectByExample(articleExample);
		return list;
	}

	// 查看我的 放在草稿箱的 资讯
	public List<Article> findMyDrafts(int userId) {
		articleExample.createCriteria().andIspublishEqualTo(0).andUidEqualTo(userId);
		List<Article> allArticleList = articleMapper.selectByExample(articleExample);
		return allArticleList;
	}

	public int updateMyCollectionArticle(MyCollection myCollection2) {
		int result = myCollectionMapper.updateByPrimaryKeySelective(myCollection2);
		return result;
	}

	//
	public MyCollection findCollectionArticle(int articleId, int userId) {
		myCollectionExample.createCriteria().andMyCollectionOnArticleIdEqualTo(articleId);
		List<MyCollection> selectByExample = myCollectionMapper.selectByExample(myCollectionExample);
		MyCollection myCollection2 = new MyCollection();
		if (null != selectByExample && selectByExample.size() > 0) {
			myCollection2 = selectByExample.get(0);
		} else {
			myCollection2 = null;
		}
		return myCollection2;
	}

	// 当 表中么有 这个记录的时候 插入一条 关注专家
	public int insertArticle(int articleId, int uId) {
		MyCollection myCollection2 = findCollectionArticle(articleId, uId);

		int result = 0;
		// 当值为
		if (myCollection2 == null || myCollection2.equals("")) {
			myCollection.setIsArticle(1);
			// 关注的专家Id
			myCollection.setMyCollectionOnArticleId(articleId);
			myCollection.setuId(uId);
			result = myCollectionMapper.insert(myCollection);
		}
		return result;
	}

	// 当表中 有这条记录 更新 isuser 为 1
	public int updateArticleToAttention(int articleId, int uId) {
		MyCollection myCollection2 = findCollectionArticle(articleId, uId);
		int num = 0;
		if (myCollection2 != null && !myCollection2.equals("") && myCollection2.getIsArticle() == 0) {
			myCollection2.setIsArticle(1);
			num = updateMyCollectionArticle(myCollection2);
		}
		return num;
	}

	// 取消关注
	public int updateArticleToNOAttention(int articleId, int uId) {
		MyCollection myCollection2 = findCollectionArticle(articleId, uId);
		int num = 0;
		if (myCollection2 != null && !myCollection2.equals("") && myCollection2.getIsArticle() == 1) {
			myCollection2.setIsArticle(0);
			num = updateMyCollectionArticle(myCollection2);
		}
		return num;
	}

	//我收藏的文章  只有能显示出来   才可以关注  所以  只需要在查询列表的时候   限制 可用的资讯   才能显示
	public List<MyCollection> findMyattentionArticle(int userId){
		MyCollectionExample myCE = new MyCollectionExample();
		myCE.createCriteria().andUIdEqualTo(userId).andIsArticleEqualTo(1);
		List<MyCollection> list = myCollectionMapper.selectByExample(myCE);
		return list;
	}

	public List<Review> findReviewByFloorAndArticleId(int floor, int articleId) {
		// TODO Auto-generated method stub
		reviewExample.createCriteria().andArticle_idEqualTo(articleId).andFloor_numberEqualTo(floor);
		List<Review> list = reviewMapper.selectByExample(reviewExample);
		return list;
	}
	
	
	public int updateReview(Review revi){
		int result = reviewMapper.updateByPrimaryKey(revi);
		return result;
	}

	public List<Article> findArticleBySeeCount() {
		ArticleExample ae = new ArticleExample();
		ae.createCriteria().andIdentifyingCodeNotEqualTo("0");
		ae.setOrderByClause("seeCount desc");
		List<Article> articleList = articleMapper.selectByExample(ae);
		return articleList;
	}
}
