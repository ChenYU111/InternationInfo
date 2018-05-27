package com.internation.info.controller.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.internation.info.Config.AddMD5Encode;
import com.internation.info.controller.question.questionController;
import com.internation.info.dao.ArticleMapper;
import com.internation.info.dao.IntegrationMapper;
import com.internation.info.dao.UserMapper;
import com.internation.info.model.Answer;
import com.internation.info.model.Article;
import com.internation.info.model.Integration;
import com.internation.info.model.IntegrationExample;
import com.internation.info.model.OtherInfo;
import com.internation.info.model.Question;
import com.internation.info.model.QuestionRevert;
import com.internation.info.model.Revert;
import com.internation.info.model.Review;
import com.internation.info.model.User;
import com.internation.info.model.UserExample;
import com.internation.info.service.InfoService;
import com.internation.info.service.IntegrationService;
import com.internation.info.service.OtherInfoService;
import com.internation.info.service.QuestionService;
import com.internation.info.service.RevertService;
import com.internation.info.service.UserService;
import com.internation.info.service.professorService;
import com.internation.info.vo.answerVo;
import com.internation.info.vo.articleVo;
import com.internation.info.vo.professDetailVo;
import com.internation.info.vo.questionRevertVo;
import com.internation.info.vo.revertVo;
import com.internation.info.vo.reviewVo;
import com.internation.info.vo.userDetailVo;

@Controller
public class userController {
	Logger logger = LoggerFactory.getLogger(userController.class);
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserExample userExample;
	@Autowired
	AddMD5Encode md5Encode;
	@Autowired
	User user;
	@Autowired
	professorService professorService;
	@Autowired
	QuestionService questionService;
	@Autowired
	InfoService infoservice;
	@Autowired
	UserService userService;
	@Autowired
	InfoService infoService;
	@Autowired
	IntegrationService integrationService;
	@Autowired
	OtherInfoService otherInfoService;
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	RevertService revertService;
	/*
	 * @Autowired User user;
	 */
	@RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
	public String loginTest() {
		return "login";
	}

	@RequestMapping(value = "/loginSure", method = { RequestMethod.POST })
	public String login(String username, String password, HttpServletRequest req, Model model) {
		System.out.println("当前用户名：" + username);
		Subject currentUser = SecurityUtils.getSubject();
		logger.info("检测用户" + username + "进行登录认证。。。。。");
		if (!currentUser.isAuthenticated()) {// 用户是否已经登录
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			logger.info(username + "认证成功。。。");
			try {
				currentUser.login(token);// 登录成功把用户的信息放到session中
				HttpSession session = req.getSession();
				userExample.createCriteria().andUserNameEqualTo(username);
				List<User> ulist = userMapper.selectByExample(userExample);
				if (ulist != null && ulist.size() > 0) {
					int userId = ulist.get(0).getId();
					session.setAttribute("userId", userId);
					session.setAttribute("userName", ulist.get(0).getUserName());
				}
				logger.info("用户" + username + "登录认证通过");
				req.setAttribute("user", ulist.get(0));
				List<User> professorList = professorService.findProfessorList();
				List<Integration> integrationList = new ArrayList<>();
				if (professorList != null && professorList.size() > 0) {
					for (User user : professorList) {
						Integration integration = professorService.findIntegrationByUId(user.getId());
						// 比较积分大小，得到积分前五的显示
						integrationList.add(integration);
					}
					Collections.sort(integrationList);
				}
				List<userDetailVo> list = new ArrayList<>();
				if (integrationList.size() > 5) {
					int index = 0;
					for (Integration integration : integrationList) {
						if (index < 5) {
							User user = professorService.findUserByUserId(integration.getUserId());
							userDetailVo vo = new userDetailVo();
							vo.setUserName(user.getUserName());
							vo.setId(user.getId());
							list.add(vo);
						}

					}
				} else {
					for (Integration integration : integrationList) {
						User user = professorService.findUserByUserId(integration.getUserId());
						userDetailVo vo = new userDetailVo();
						vo.setUserName(user.getUserName());
						vo.setId(user.getId());
						list.add(vo);
					}
				}

				model.addAttribute("professVoList", list);
				// top10 文章
				List<Article> articlelist = infoService.findArticleBySeeCount();
				if (articlelist != null && articlelist.size() > 0) {
					int index = 0;
					List<articleVo> articleVoList = new ArrayList<>();
					for (Article ar : articlelist) {
						if (index < 10) {
							index++;
							articleVo articleVo = new articleVo();
							articleVo.setTitle(ar.getTitle());
							articleVo.setSeecount(ar.getSeecount());
							User user = userService.findUserByPKId(ar.getUid());
							articleVo.setUsername(user.getUserName());
							articleVo.setId(ar.getId());
							articleVoList.add(articleVo);
						}
					}
					model.addAttribute("articleList", articleVoList);
				
				}
				
				List<OtherInfo> list2 = otherInfoService.findOtherInfoTop10();
				List<OtherInfo> top10List = new ArrayList<>();
				int index = 0;
				if (list2 != null && list2.size() > 0) {
					if (list2.size() > 10) {
						if (index < 10) {
							for (OtherInfo otherInfo : list2) {
								index++;
								top10List.add(otherInfo);
							}
						}
					}else{
						for (OtherInfo otherInfo : list2) {
							top10List.add(otherInfo);
						}
					}
				}
				model.addAttribute("otherInfoList", top10List);
				String un = (String)req.getSession().getAttribute("username");
				model.addAttribute("username", (String)req.getSession().getAttribute("userName"));
				return "main";
			} catch (AuthenticationException e) {
				System.out.println("登录失败");
			}
		} else {
			return "main";
		}
		return "login";
	}

	// 注册确定Controller
	@RequestMapping("register")
	public String register() {
		return "register";
	}

	@RequestMapping("/getUser")
	public String UserInfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", userMapper.selectByPrimaryKey(1));
		return "userInfo";
	}

	/*
	 * @RequestMapping("/") public String index(){
	 * System.out.println("nanshou=========="); return "index"; }
	 */

	@RequestMapping(value="/toregister",method = { RequestMethod.POST })
	public String toRegister(@ModelAttribute User user, Model model) {
		System.out.println(user.getUserName());
		String password = md5Encode.md5Pwd(user.getPassword(), user.getUserName());
		user.setPassword(password);
		user.setCreateTime(new Date());
		user.setStatus(1);
		user.setSalt(user.getUserName());
		int num = userMapper.insert(user);
		logger.info("用户注册是否成功" + num);
		if (num >0) {
			Integration integrat = new Integration();
			integrat.setIntegration_number(100);
			List<User> allUser = userService.findAllUser();
			User user2 = new User();
			if(allUser!=null&&allUser.size()>0){
				user2 = allUser.get(allUser.size()-1);
			}
			integrat.setUserId(user2.getId());
			integrationService.insert(integrat);
			return "login";
		} else {
			return "register";
			
		}
	}

	@RequestMapping("/main")
	public String toMain(Model model,HttpServletRequest req) {
		List<User> professorList = professorService.findProfessorList();
		List<Integration> integrationList = new ArrayList<>();
		if (professorList != null && professorList.size() > 0) {
			for (User user : professorList) {
				Integration integration = professorService.findIntegrationByUId(user.getId());
				// 比较积分大小，得到积分前五的显示
				integrationList.add(integration);
			}
			Collections.sort(integrationList);
		}
		List<userDetailVo> list = new ArrayList<>();
		if (integrationList.size() > 5) {
			int index = 0;
			for (Integration integration : integrationList) {
				if (index < 5) {
					User user = professorService.findUserByUserId(integration.getUserId());
					userDetailVo vo = new userDetailVo();
					vo.setUserName(user.getUserName());
					vo.setId(user.getId());
					list.add(vo);
				}
			}
		} else {
			for (Integration integration : integrationList) {
				User user = professorService.findUserByUserId(integration.getUserId());
				userDetailVo vo = new userDetailVo();
				vo.setUserName(user.getUserName());
				vo.setId(user.getId());
				list.add(vo);
			}
		}

		model.addAttribute("professVoList", list);
		// top10 文章
		List<Article> articlelist = infoService.findArticleBySeeCount();
		if (articlelist != null && articlelist.size() > 0) {
			int index = 0;
			List<articleVo> articleVoList = new ArrayList<>();
			for (Article ar : articlelist) {
				if (index < 10) {
					index++;
					articleVo articleVo = new articleVo();
					articleVo.setTitle(ar.getTitle());
					articleVo.setSeecount(ar.getSeecount());
					User user = userService.findUserByPKId(ar.getUid());
					articleVo.setUsername(user.getUserName());
					articleVo.setId(ar.getId());
					articleVoList.add(articleVo);
				}
			}
			model.addAttribute("articleList", articleVoList);
		}
		List<OtherInfo> list2 = otherInfoService.findOtherInfoTop10();
		List<OtherInfo> top10List = new ArrayList<>();
		int index = 0;
		if (list2 != null && list2.size() > 0) {
			if (list2.size() > 10) {
				if (index < 10) {
					for (OtherInfo otherInfo : list2) {
						index++;
						top10List.add(otherInfo);
					}
				}
			}else{
				for (OtherInfo otherInfo : list2) {
					top10List.add(otherInfo);
				}
			}
		}
		model.addAttribute("otherInfoList", top10List);
		model.addAttribute("username", (String)req.getSession().getAttribute("userName"));
		return "main";
	}

	@RequestMapping("/logout")
	public String toLogout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
			/*
			 * if (LOG.isDebugEnabled()) { LOG.debug("用户" + username + "退出登录");
			 * }
			 */// 提示用户退出成功。。。
		}
		return "login";
	}

	@RequestMapping("/seeUserDetail")
	public String seeUserDetail(Model model, HttpServletRequest req) {
		int uId = (int) req.getSession().getAttribute("userId");
		User user1 = userMapper.selectByPrimaryKey(uId);
		userDetailVo userDetailVo = new userDetailVo();
		userDetailVo.setUserName(user1.getUserName());
		userDetailVo.setCreateTime(user1.getCreateTime());
		userDetailVo.setSex(user1.getSex());
		userDetailVo.setTel(user.getTel());
		if (user1.getIsprofessor() != null && user1.getIsprofessor() == 3 && null != user1.getProfessorRemark()
				&& user1.getProfessorRemark().equals("审核通过")) {
			userDetailVo.setIsprofessor(1);
		} else {
			userDetailVo.setIsprofessor(0);
		}
		int integrationCount = professorService.findIntegration(uId);
		List<Question> findMyQuestionList = questionService.findMyQuestion(uId);
		int questionCount = (findMyQuestionList == null || findMyQuestionList.size() == 0) ? 0
				: findMyQuestionList.size();
		List<Article> MyArticleList = infoservice.findMyArticleById(uId);
		int articleCount = (MyArticleList == null || MyArticleList.size() == 0) ? 0 : MyArticleList.size();
		userDetailVo.setIntegration(integrationCount);
		userDetailVo.setQuestionCount(questionCount);
		userDetailVo.setArticleCount(articleCount);
		model.addAttribute("userDetailVo", userDetailVo);
		professDetailVo professDetailVo = new professDetailVo();
		List<Article> articleList = professorService.findPublishArticleCount(uId);
		if (null != articleList && articleList.size() > 0) {
			// 统计专家类别
			List<String> typeCount = new ArrayList<>();
			HashMap<String, Integer> typeMap = new HashMap<>();
			for (Article article : articleList) {
				if (article.getBlog_type().contains("Java")) {
					if (typeMap.get("Java") == null) {
						typeMap.put("Java", 1);
					} else {
						typeMap.put("Java", typeMap.get("Java") + 1);
					}
				}
				if (article.getBlog_type().contains("php")) {
					if (typeMap.get("php") == null) {
						typeMap.put("php", 1);
					} else {
						typeMap.put("php", typeMap.get("php") + 1);
					}
				}
				if (article.getBlog_type().contains("数据库")) {
					if (typeMap.get("数据库") == null) {
						typeMap.put("数据库", 1);
					} else {
						typeMap.put("数据库", typeMap.get("数据库") + 1);
					}
				}
				if (article.getBlog_type().contains("人工智能")) {
					if (typeMap.get("人工智能") == null) {
						typeMap.put("人工智能", 1);
					} else {
						typeMap.put("人工智能", typeMap.get("人工智能") + 1);
					}
				}

			}
			// Collections.sort(list, c);
			// https://www.cnblogs.com/liujinhong/p/6113183.html
			List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(typeMap.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
				// 升序排序
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}

			});
			StringBuffer type = new StringBuffer();
			if (list != null && list.size() > 3) {
				int num = 0;
				if (num < 3) {
					for (Entry<String, Integer> entry : list) {
						System.out.println(entry.getKey() + ":" + entry.getValue());
						type.append(entry.getKey()).append(",");
					}
				}

			} else if (list != null && list.size() > 0 && list.size() < 3) {
				for (Entry<String, Integer> entry : list) {
					System.out.println(entry.getKey() + ":" + entry.getValue());
					type.append(entry.getKey()).append(",");
				}
			}
			String professerType = type.toString();
			if (professerType.length() > 1) {
				professerType = professerType.substring(0, professerType.length() - 1);
			}
			System.out.println(professerType);
			professDetailVo.setProfessorType(professerType);
		}
		professDetailVo.setIntegration(integrationCount);
		professDetailVo.setArticleCount(articleCount);
		model.addAttribute("professDetailVo", professDetailVo);
		List<Article> myArticleById = infoservice.findMyArticleById(uId);
		if (myArticleById != null && !myArticleById.equals("")) {
			model.addAttribute("articleList", articleList);
		} else {
			model.addAttribute("articleList", "您还没有写过文章，点击上面的 写资讯 来写一篇吧！");
		}

		List<Question> findMyQuestion = questionService.findMyQuestion(uId);
		if (findMyQuestion != null && findMyQuestion.size() > 0) {
			model.addAttribute("myQuestionList", findMyQuestion);
		}
		return "user/seeUserDetail";
	}

	@RequestMapping("/searchseeUserDetail/{id}")
	public String searchSeeUserDetail(@PathVariable("id") Integer uId, Model model, HttpServletRequest req) {
		// int uId = (int) req.getSession().getAttribute("userId");
		User user1 = userMapper.selectByPrimaryKey(uId);
		userDetailVo userDetailVo = new userDetailVo();
		userDetailVo.setUserName(user1.getUserName());
		userDetailVo.setCreateTime(user1.getCreateTime());
		userDetailVo.setSex(user1.getSex());
		userDetailVo.setTel(user.getTel());
		if (user1.getIsprofessor() == 3 && null != user1.getProfessorRemark()
				&& user1.getProfessorRemark().equals("审核通过")) {
			userDetailVo.setIsprofessor(1);
		} else {
			userDetailVo.setIsprofessor(0);
		}
		int integrationCount = professorService.findIntegration(uId);
		List<Question> findMyQuestionList = questionService.findMyQuestion(uId);
		int questionCount = (findMyQuestionList == null || findMyQuestionList.size() == 0) ? 0
				: findMyQuestionList.size();
		List<Article> MyArticleList = infoservice.findMyArticleById(uId);
		int articleCount = (MyArticleList == null || MyArticleList.size() == 0) ? 0 : MyArticleList.size();
		userDetailVo.setIntegration(integrationCount);
		userDetailVo.setQuestionCount(questionCount);
		userDetailVo.setArticleCount(articleCount);
		model.addAttribute("userDetailVo", userDetailVo);
		return "user/seeUserDetail";
	}

	@RequestMapping("/updatePassword")
	public String updatePassword(String userName, String newpassword, String surepasswore, HttpServletRequest req,
			Model model) {
		String str = "";
		User user2 = userMapper.selectByPrimaryKey((int) req.getSession().getAttribute("userId"));

		if (user2 != null) {
			if (user2.getUserName().equals(userName)) {
				String pwd = md5Encode.md5Pwd(newpassword, user2.getUserName());
				// 如果新设置的密码跟原始密码一样 不可修改
				if (user2.getPassword().equals(pwd)) {
					str = "你输入的密码跟原密码一样，不能修改！";
					model.addAttribute("result", str);
				}
				// 如果 新密码跟确认密码一样，可以修改
				if (newpassword.equals(surepasswore)) {
					user2.setPassword(md5Encode.md5Pwd(newpassword, user2.getUserName()));
					int result = userMapper.updateByPrimaryKeySelective(user2);
					if (result > 0) {
						str = "修改成功!";
						model.addAttribute("result", str);
					} else {
						str = "修改失败！";
						model.addAttribute("result", str);
					}
				}
			} else {
				str = "您输入的用户名有误！";
				model.addAttribute("result", str);
			}
		} else {
			str = "你不是本系统用户，请注册！";
			model.addAttribute("result", str);
		}

		return "user/updatePasswordResult";
	}

	@RequestMapping("/updatepwd")
	public String updatepwd() {
		return "user/updatePassword";
	}

	@RequestMapping("/findUserByUserNameLike")
	public List<User> findUserByUserNameLike(String str, Model model) {
		List<User> list = userService.findUserByLikeUsername(str);
		return list;
	}
	
	// 查看文章 和 本文章的资讯 如果 有 评论 就 展示出来
	@RequestMapping("/seeOneArticle/user/{id}")
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
		return "user/seeArticleDetail";
	}
	@RequestMapping("/seeQuestionDetail/user/{id}")
	public String seeQuestionDetail(@PathVariable("id") Integer questionId, Model model, HttpServletRequest req) {
		Question findQuestionDetailById = questionService.findQuestionDetailById(questionId);
		int seecount = 0;
		if (findQuestionDetailById.getSeeCount() != null) {
			seecount = findQuestionDetailById.getSeeCount() + 1;
		}
		findQuestionDetailById.setSeeCount(seecount + 1);
		int result = questionService.updateQuestion(findQuestionDetailById);
		if (result > 0) {
			findQuestionDetailById = questionService.findQuestionDetailById(questionId);
		}
		model.addAttribute("questionDetail", findQuestionDetailById);
		List<Answer> findQuestionAnswerById = questionService.findQuestionAnswerById(questionId);
		List<answerVo> answerVoList = new ArrayList<>();
		if (findQuestionAnswerById != null && findQuestionAnswerById.size() > 0) {
			for (Answer answer : findQuestionAnswerById) {
				answerVo answerVo = new answerVo();
				answerVo.setId(answer.getId());
				answerVo.setContent(answer.getContent());
				answerVo.setAnswerTime(answer.getAnswerTime());
				answerVo.setFloor(answer.getFloor());
				answerVo.setIsAdopt(answer.getIsAdopt());
				answerVo.setQuestionId(answer.getQuestionId());
				User user = userService.findUserByPKId((int) req.getSession().getAttribute("userId"));
				answerVo.setUserName(user.getUserName());
				answerVo.setuId(user.getId());
				List<QuestionRevert> answerRevertList = questionService.findQuestionAnswerRevert(questionId,answer.getFloor());
				if (answerRevertList != null && answerRevertList.size() > 0) {
					List<questionRevertVo> questionRevertVoList = new ArrayList<>();
					for (QuestionRevert questionRevert2 : answerRevertList) {
						questionRevertVo qvo = new questionRevertVo();
						qvo.setId(questionRevert2.getId());
						qvo.setCreateTime(questionRevert2.getCreateTime());
						qvo.setQuestionFloor(questionRevert2.getQuestionFloor());
						qvo.setRevertFloor(questionRevert2.getRevertFloor());
						qvo.setRevertMessage(questionRevert2.getRevertMessage());
						User user2 = userService.findUserByPKId((int) req.getSession().getAttribute("userId"));
						qvo.setuId(user2.getId());
						qvo.setUsername(user2.getUserName());
						questionRevertVoList.add(qvo);
					}
					answerVo.setQuestionRevertVoList(questionRevertVoList);
				}
				answerVoList.add(answerVo);
			}
		}
		model.addAttribute("answerVoList", answerVoList);
		HttpSession session = req.getSession();
		session.setAttribute("seeQuestionId", questionId);
		return "user/seeQuestionDetail";
	}
}
