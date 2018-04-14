package com.internation.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testHtml {
	
	@RequestMapping("/m")
	public String Main(){
		return "main";
	}
	@RequestMapping("/")
	public String toMain(){
		return "main";
	}
	
}
