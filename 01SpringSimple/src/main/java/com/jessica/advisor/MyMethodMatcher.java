package com.jessica.advisor;

import java.lang.reflect.Method;

import org.springframework.aop.MethodMatcher;

public class MyMethodMatcher implements MethodMatcher {

	@Override
	public boolean isRuntime() {
		// TODO Auto-generated method stub
		return false;
	}

	/* 只根据方法名称进行判断 */
	@Override
	public boolean matches(Method arg0, Class<?> arg1) {
		if (arg0.getName().equals("eat")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean matches(Method arg0, Class<?> arg1, Object... arg2) {
		// TODO Auto-generated method stub
		return false;
	}

}
