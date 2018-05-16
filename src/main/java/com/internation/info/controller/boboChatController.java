package com.internation.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class boboChatController {

	@RequestMapping("/boboChat")
	public String index() {
		return "chat/boboChat";
	}

}
