package com.jessica.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class SingleMapperController extends AbstractController {
	private static int counter = 0;
	private static final String VIEW_INDEX = "home";

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mv = new ModelAndView();
		ModelMap model = new ModelMap();
		model.addAttribute("message", "Welcome ");
		model.addAttribute("counter", ++counter);
		// 模型
		mv.addObject("message", "test");
		// 视图
		mv.setViewName(VIEW_INDEX);
		return mv;
	}
}