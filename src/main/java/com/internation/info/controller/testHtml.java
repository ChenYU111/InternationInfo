package com.internation.info.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internation.info.model.Article;
import com.internation.info.model.Integration;
import com.internation.info.model.User;
import com.internation.info.service.InfoService;
import com.internation.info.service.UserService;
import com.internation.info.service.professorService;
import com.internation.info.vo.articleVo;
import com.internation.info.vo.userDetailVo;

@Controller
public class testHtml {
	@Autowired
	professorService professorService;
	@Autowired
	InfoService infoService;
	@Autowired
	UserService userService;

	@RequestMapping("/m")
	public String Main(Model model) {
		List<User> professorList = professorService.findProfessor();
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
		return "main";
	}

	@RequestMapping("/")
	public String toMain(Model model) {
		List<User> professorList = professorService.findProfessor();
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
		return "main";
	}

	@RequestMapping("/d")
	public String dialog() {
		return "info/dialog";
	}

}
