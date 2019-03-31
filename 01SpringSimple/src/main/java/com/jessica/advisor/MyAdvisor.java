package com.jessica.advisor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

public class MyAdvisor implements PointcutAdvisor {

	private Advice advice;

	public Pointcut getPointCut() {
		return pointCut;
	}

	public void setPointCut(Pointcut pointCut) {
		this.pointCut = pointCut;
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

	private Pointcut pointCut;

	@Override
	public Advice getAdvice() {
		return this.advice;
	}

	@Override
	public boolean isPerInstance() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pointcut getPointcut() {
		return this.pointCut;
	}

}
