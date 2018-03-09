package com.internation.info.controller.user;

import java.util.Date;

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
import com.internation.info.dao.UserMapper;
import com.internation.info.model.User;

@Controller
public class userLogin {
	Logger logger = LoggerFactory.getLogger(userLogin.class);
	@Autowired
	UserMapper userMapper;
	@Autowired
	AddMD5Encode md5Encode;

	/*
	 * @Autowired User user;
	 */
	@RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
	public String loginTest() {
		return "login";
	}

	@RequestMapping(value = "/loginSure", produces = "application/json; charset=utf-8", method = { RequestMethod.POST })
	public String login(String username, String password) {
		System.out.println("当前用户名：" + username);
		Subject currentUser = SecurityUtils.getSubject();
		logger.info("检测用户" + username + "进行登录认证。。。。。");
		if (!currentUser.isAuthenticated()) {// 用户是否已经登录
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			logger.info(username + "认证成功。。。");
			try {
				currentUser.login(token);
				logger.info("用户" + username + "登录认证通过");
				return "main";
			} catch (AuthenticationException e) {
				System.out.println("登录失败");
			}
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

	@RequestMapping("/toregister")
	public String toRegister(@ModelAttribute User user, Model model) {
		System.out.println(user.getUserName());
		String password = md5Encode.md5Pwd(user.getPassword(), user.getUserName());
		user.setPassword(password);
		user.setCreateTime(new Date());
		user.setRoleId("1,3,2");
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
}
