package com.internation.info.controller.aboutUs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class aboutUsController {
	@RequestMapping("/aboutUs")
	public String toAboutUs(){
		return "aboutUs/aboutUs";
	}
}
