package com.internation.info.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.internation.info.Config.AddMD5Encode;
import com.internation.info.controller.question.questionController;
import com.internation.info.dao.UserMapper;
import com.internation.info.model.Article;
import com.internation.info.model.Question;
import com.internation.info.model.User;
import com.internation.info.model.UserExample;
import com.internation.info.service.InfoService;
import com.internation.info.service.QuestionService;
import com.internation.info.service.professorService;
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
	/*
	 * @Autowired User user;
	 */
	@RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
	public String loginTest() {
		return "login";
	}

	@RequestMapping(value = "/loginSure", method = { RequestMethod.POST })
	public String login(String username, String password,HttpServletRequest req) {
		System.out.println("当前用户名：" + username);
		Subject currentUser = SecurityUtils.getSubject();
		logger.info("检测用户" + username + "进行登录认证。。。。。");
		if (!currentUser.isAuthenticated()) {// 用户是否已经登录
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			logger.info(username + "认证成功。。。");
			try {
				currentUser.login(token);//登录成功把用户的信息放到session中
				HttpSession session = req.getSession();
				userExample.createCriteria().andUserNameEqualTo(username);
				List<User> ulist = userMapper.selectByExample(userExample);
				if(ulist!=null&&ulist.size()>0){
					int userId =ulist.get(0).getId();
					session.setAttribute("userId", userId);
					session.setAttribute("userName", ulist.get(0).getUserName());
				}
				logger.info("用户" + username + "登录认证通过");
				req.setAttribute("user", ulist.get(0));
				return "user/successMain";
			} catch (AuthenticationException e) {
				System.out.println("登录失败");
			}
		}/*else{
			return "main";
		}*/
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

	@RequestMapping("/toregister")
	public String toRegister(@ModelAttribute User user, Model model) {
		System.out.println(user.getUserName());
		String password = md5Encode.md5Pwd(user.getPassword(), user.getUserName());
		user.setPassword(password);
		user.setCreateTime(new Date());
		user.setStatus(1);
		user.setSalt(user.getUserName());
		int num = userMapper.insert(user);
		logger.info("用户注册是否成功" + num);
		if (num == 0) {
			return "register";
		} else {
			return "login";
		}
	}

	@RequestMapping("/main")
	public String toMain() {
		return "main";
	}
	@RequestMapping("/logout")
	public String toLogout(){
		 Subject subject = SecurityUtils.getSubject();  
		    if (subject.isAuthenticated()) {  
		        subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存  
		        /*if (LOG.isDebugEnabled()) {  
		            LOG.debug("用户" + username + "退出登录");  
		        }  *///提示用户退出成功。。。
		    }  
		return "login";
	}
	
	@RequestMapping("/seeUserDetail")
	public String seeUserDetail(Model model,HttpServletRequest req){
		int uId = (int) req.getSession().getAttribute("userId");
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
	public String updatePassword(User u,HttpServletRequest req,Model model){
		User user2 = userMapper.selectByPrimaryKey((int)req.getSession().getAttribute("userId"));
		if(user2!=null){
			String password = md5Encode.md5Pwd(u.getPassword(), user2.getUserName());
			user2.setPassword(password);
			int result = userMapper.updateByPrimaryKeySelective(user2);
			if(result>0){
				model.addAttribute("result", "修改成功");
			}else {
				model.addAttribute("result", "修改失败");
			}
		}
		return "user/updatePasswordResult";
	}
	
	@RequestMapping("/updatepwd")
	public String updatepwd(){
		return "user/updatePassword";
	}
}
