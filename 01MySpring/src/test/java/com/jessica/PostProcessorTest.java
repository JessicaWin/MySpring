package com.jessica;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.jessica.bean.MySpringXmlApplicationContext;
import com.jessica.service.BaseService;

public class PostProcessorTest {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void testPostProcessor() throws Exception {
		MySpringXmlApplicationContext context = new MySpringXmlApplicationContext(
				"src/main/resources/com/jessica/config/spring-config-processor.xml");
		BaseService baseService = (BaseService) context.getBean("myService");
		String result = baseService.doSomething();
		logger.debug(result);
		assertEquals("HELLO, JESSICA.", result);

	}
}
