package com.internation.info.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internation.info.model.OtherInfo;
import com.internation.info.service.OtherInfoService;
@Controller
public class otherInfoController {
	@Autowired
	OtherInfoService otherInfoService;
	@RequestMapping("/otherInfo")
	public String findAllOtherInfo(Model model){
		List<OtherInfo> list = otherInfoService.findAllOtherInfo();
		model.addAttribute("otherInfoList", list);
		return "otherInfoList";
	}
	
	@RequestMapping("/otherInfoDetail/{id}")
	public String findOtherInfoDetail(@PathVariable("id") int oId,Model model){
		System.out.println(oId);
		OtherInfo findOtherInfo = otherInfoService.findOtherInfo(oId);
		model.addAttribute("otherInfo", findOtherInfo);
		return "otherInfoDetail";
	}
}
