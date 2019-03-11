package com.jessica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jessica.service.Animal;

public class BeforeAdviceTest {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void testPostProcessor() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/jessica/config/spring-config-advice.xml");
		Animal animal = (Animal) context.getBean("personProxy");
		animal.eat();
		animal.wc();
	}

}
