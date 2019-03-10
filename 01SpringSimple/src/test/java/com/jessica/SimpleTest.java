package com.jessica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jessica.entity.Student;
import com.jessica.entity.Teacher;

public class SimpleTest {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void testBeanDefination() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/jessica/config/spring-config.xml");
		Teacher teacher = (Teacher) context.getBean("teacher");
		logger.info(teacher);
		Student student = (Student) context.getBean("student");
		logger.info(student);
		Teacher teacherByFactory = (Teacher) context.getBean("teacherByFactory");
		logger.info(teacherByFactory);
	}
}
