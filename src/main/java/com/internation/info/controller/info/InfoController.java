package com.internation.info.controller.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InfoController {
	@RequestMapping("/writeInfo")
	public String addInfo(){
		return "info/writeInfo";
	}
	
	
	//提交资讯
	@RequestMapping("/submitInfo")
	public String commitInfo(){
		
		return "";
	}
	//保存到草稿箱
	public String saveInfo(){
		return "save";
	}

}
