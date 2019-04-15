package com.jessica.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jessica.exception.BusinessException;

public class BusinessExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		BusinessException businessException = null;
		if (ex instanceof BusinessException) {
			businessException = (BusinessException) ex;
		} else {
			businessException = new BusinessException("Unknown exception");
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", businessException.getMessage());
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
