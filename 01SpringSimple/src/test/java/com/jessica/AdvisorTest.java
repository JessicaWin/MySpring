package com.jessica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jessica.service.Animal;

public class AdvisorTest {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void testAdvisor() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/com/jessica/config/spring-config-advisor.xml");
		Animal persoon = (Animal) context.getBean("personProxy");
		persoon.eat();
		persoon.wc();
		Animal dog = (Animal) context.getBean("dogProxy");
		dog.eat();
		dog.wc();
	}

}
