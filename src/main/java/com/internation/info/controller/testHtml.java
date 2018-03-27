package com.internation.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testHtml {
	@RequestMapping("/t")
	public String test(){
		return "test";
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
