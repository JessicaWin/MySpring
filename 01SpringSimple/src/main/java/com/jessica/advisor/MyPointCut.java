package com.jessica.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

public class MyPointCut implements Pointcut {
	private ClassFilter classFilter;
	private MethodMatcher methodMatcher;

	public void setClassFilter(ClassFilter classFilter) {
		this.classFilter = classFilter;
	}

	public void setMethodMatcher(MethodMatcher methodMatcher) {
		this.methodMatcher = methodMatcher;
	}

	@Override
	public ClassFilter getClassFilter() {
		return this.classFilter;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return this.methodMatcher;
	}

}
