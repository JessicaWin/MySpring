package com.jessica.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/springDaoConfig.xml", "classpath:spring/springServiceConfig.xml",
		"classpath:spring/springTxConfig.xml" })
public class AccountServiceImplTest {

	@Autowired
	private AccountService accountService;

	@Test
	public void test() {
		accountService.pay("ChenLu", "DaiCong", 100);
	}
}
