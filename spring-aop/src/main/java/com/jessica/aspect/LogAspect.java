package com.jessica.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	@Before(value = "execution(void com.jessica.service.impl.UserServiceImpl.insertUser(String))")
	public void before() {
		System.out.println("before aspect");
	}

	@Around(value = "execution(void com.jessica.service.impl.UserServiceImpl.updateUser(String, String))")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object returnValue = null;
		try {
			Object[] args = joinPoint.getArgs();
			System.out.println("aroung aspect before method invoke");
			returnValue = joinPoint.proceed(args);
			System.out.println("aroung aspect after method invoke");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

	}

}
