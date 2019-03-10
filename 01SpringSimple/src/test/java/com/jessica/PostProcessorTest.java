package com.jessica;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jessica.service.BaseService;

public class PostProcessorTest {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void testPostProcessor() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/com/jessica/config/spring-config-processor.xml");
		BaseService baseService = (BaseService) context.getBean("myService");
		String result = baseService.doSomething();
		logger.debug(result);
		assertEquals("HELLO, JESSICA.", result);

	}
}
