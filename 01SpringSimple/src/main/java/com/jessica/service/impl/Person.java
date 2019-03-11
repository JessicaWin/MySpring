package com.jessica.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jessica.service.Animal;

public class Person implements Animal {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void eat() {
		logger.debug("have lunch");
	}

	@Override
	public void wc() {
		logger.debug("take a shower");
	}

}
