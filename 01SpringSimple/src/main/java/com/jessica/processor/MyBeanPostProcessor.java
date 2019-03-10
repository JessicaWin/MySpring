package com.jessica.processor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.jessica.service.impl.MyService;

public class MyBeanPostProcessor implements BeanPostProcessor {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		logger.debug("Action before bean initialize....");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		logger.debug("Action After bean initialize....");
		Class beanClass = bean.getClass();
		if (beanClass.equals(MyService.class)) {
			Object proxy = Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							if (method.getName().equals("doSomething")) {
								logger.debug("doSomething method in MyService is intercept....");
								String result = (String) method.invoke(bean, args);
								result = result.toUpperCase();
								return result;
							}
							return method.invoke(bean, args);
						}
					});
			return proxy;
		}
		return bean;
	}
}
