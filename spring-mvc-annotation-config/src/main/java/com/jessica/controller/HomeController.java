package com.jessica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private static int counter = 0;
	private static final String VIEW_INDEX = "index";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		model.addAttribute("message", "Welcome Home");
		model.addAttribute("counter", ++counter);
		return VIEW_INDEX;// 返回home.jsp
	}
}
