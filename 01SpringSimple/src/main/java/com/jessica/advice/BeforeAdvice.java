package com.jessica.advice;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class BeforeAdvice implements MethodBeforeAdvice {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		logger.debug("wash hands");
	}
}
