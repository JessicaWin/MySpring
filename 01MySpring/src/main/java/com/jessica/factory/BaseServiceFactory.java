package com.jessica.factory;

import com.jessica.service.BaseService;
import com.jessica.service.impl.MyService;

public class BaseServiceFactory {
	public BaseService createBaseService() {
		return new MyService();
	}
}
