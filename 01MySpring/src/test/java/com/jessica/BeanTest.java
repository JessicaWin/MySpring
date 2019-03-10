package com.jessica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.jessica.bean.MySpringXmlApplicationContext;
import com.jessica.entity.Student;
import com.jessica.entity.Teacher;

public class BeanTest {
	@Test
	public void testBean() throws Exception {
		MySpringXmlApplicationContext context = new MySpringXmlApplicationContext(
				"src/main/resources/com/jessica/config/spring-config.xml");
		Teacher teacherExpected = new Teacher();
		teacherExpected.setFirstName("Mayer");
		teacherExpected.setLastName("Susan");
		teacherExpected.setTeacherNo(12);
		teacherExpected.setWorkAge(3);
		Student studentExpected = new Student();
		studentExpected.setFirstName("Solis");
		studentExpected.setLastName("Gabi");
		studentExpected.setGrade("2");
		studentExpected.setSutNo(1);
		Teacher teacher = (Teacher) context.getBean("teacher");
		assertEquals(teacher.getFirstName(), teacherExpected.getFirstName());
		assertEquals(teacher.getLastName(), teacherExpected.getLastName());
		assertEquals(teacher.getTeacherNo(), teacherExpected.getTeacherNo());
		assertEquals(teacher.getWorkAge(), teacherExpected.getWorkAge());
		Student student = (Student) context.getBean("student");
		assertEquals(student.getFirstName(), studentExpected.getFirstName());
		assertEquals(student.getLastName(), studentExpected.getLastName());
		assertEquals(student.getGrade(), studentExpected.getGrade());
		assertEquals(student.getSutNo(), studentExpected.getSutNo());
		Teacher teacher2 = (Teacher) context.getBean("teacher");
		assertEquals(teacher, teacher2);
		Student student2 = (Student) context.getBean("student");
		assertNotEquals(student, student2);
	}
}
