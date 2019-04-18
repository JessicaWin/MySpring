package com.jessica.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("loginPage")
	public String login() {
		return "login/login";
	}

	// 登录
	@RequestMapping("/login")
	public String login(HttpSession session, String username, String password) {
		// Service进行用户身份验证

		// 把用户信息保存到session中
		session.setAttribute("username", username);

		// 重定向到商品列表页面
		return "redirect:/queryAllItem";
	}

	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		// 清空session
		session.invalidate();
		// 重定向到登录页面
		return "redirect:/loginPage";
	}
}
