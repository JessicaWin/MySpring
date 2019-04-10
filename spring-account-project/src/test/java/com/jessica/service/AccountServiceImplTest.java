package com.jessica.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jessica.config.RootApplicationContextConfig;
import com.jessica.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootApplicationContextConfig.class })
public class AccountServiceImplTest {

	@Autowired
	private AccountService accountService;

	@Test
	public void test() {
		accountService.pay("DaiCong", "ChenLu", 100);
	}
}
