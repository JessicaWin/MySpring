package com.jessica.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jessica.service.Animal;

public class Dog implements Animal {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void eat() {
		logger.debug("啃骨头");
	}

	@Override
	public void wc() {
		logger.debug("三腿立");
	}

}
