package com.jessica.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();

		log.debug(requestURI);

		if (requestURI.indexOf("login") > -1) {
			return true;
		}
		String userName = (String) request.getSession().getAttribute("username");
		if (userName != null && !userName.equals("")) {
			return true;
		}
		response.sendRedirect("/myspring-mvc/loginPage");
		return false;
	}
}
