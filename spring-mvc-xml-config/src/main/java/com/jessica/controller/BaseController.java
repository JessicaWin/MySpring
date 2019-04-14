package com.jessica.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
@RequestMapping(value = "/")
public class BaseController extends AbstractController {
	private static int counter = 0;
	private static final String VIEW_INDEX = "home";

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView();
		ModelMap model = new ModelMap();
		model.addAttribute("message", "Welcome Base home");
		model.addAttribute("counter", ++counter);
		// 模型
		mv.addObject("message", "test");
		// 视图
		mv.setViewName(VIEW_INDEX);
		return mv;
	}

	@Override
	@RequestMapping(value = "/base", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		ModelMap model = new ModelMap();
		model.addAttribute("message", "Welcome base base");
		model.addAttribute("counter", ++counter);
		// 模型
		mv.addObject("message", "test");
		// 视图
		mv.setViewName(VIEW_INDEX);
		return mv;
	}
}