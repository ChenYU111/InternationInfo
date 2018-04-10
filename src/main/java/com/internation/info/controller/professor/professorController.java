package com.internation.info.controller.professor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.internation.info.model.User;
import com.internation.info.service.professorService;
@Controller
public class professorController {
	@Autowired
	professorService professorService;
	@RequestMapping("/professorList")
	public String findAllProfessorList(Model model){
		List<User> professorList = professorService.findProfessorList();
		model.addAttribute("professorList", professorList);
		return "professor/professorList";
	}
	
	@RequestMapping("/application")
	public String appliciton(HttpServletRequest req,Model model){
		HttpSession session = req.getSession();
		int userId = (int) session.getAttribute("userId");
		model.addAttribute("userId", userId);
		return "professor/applicationProfessor";
	}
	
	
	@RequestMapping("/sureApplication/{id}")
	@ResponseBody
	public void applicationProfessor(@PathVariable("id") Integer userId, Model model) {
		User user = professorService.findUserByUserId(userId);
		String result = "";
		// 更改 提交审核 为 2 状态
		if (user.getIsprofessor() != 1) {
			user.setIsprofessor(2);
			int num = professorService.updateUser(user);
			if (num == 1) {
				result = "已经申请,等待管理员审核";
			} else {
				result = "申请失败";
			}
		} else {
			result="不可以申请，您已经是专家。";
		}
		model.addAttribute("result", result);
	}
}
