package com.internation.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testHtml {
	@RequestMapping("/")
	public String test(){
		return "main";
	}
	@RequestMapping("/m")
	public String testMain(){
		return "main";
	}
	@RequestMapping("/index")
	public String testLayout(){
		return "layout";
	}
}
