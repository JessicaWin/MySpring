package com.jessica.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jessica.entity.Teacher;

public class TeacherFactory {
	private Logger logger = LogManager.getLogger(this.getClass());

	public Teacher creaTeacher() {
		Teacher teacher = new Teacher();
		logger.debug("Create a teacher instance by TeacherFactory");
		return teacher;
	}
}
