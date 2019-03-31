package com.jessica.advisor;

import org.springframework.aop.ClassFilter;

import com.jessica.service.impl.Person;

public class MyClassFilter implements ClassFilter {
	/**
	 * 
	 * 1.一个接口下面有多个实现类 2.判断当前对象是不是我们织入方式关心的目标类
	 */
	@Override
	public boolean matches(Class<?> arg0) {
		if (arg0.equals(Person.class)) {
			return true;
		}
		return false;
	}

}
