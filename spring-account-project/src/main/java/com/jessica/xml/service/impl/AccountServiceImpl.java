package com.jessica.xml.service.impl;

import com.jessica.dao.AccountDao;
import com.jessica.xml.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void pay(String inName, String outName, int money) {
		accountDao.outMoney(outName, money);
		System.out.println(10 / 0);
		accountDao.inMoney(inName, money);
	}
}
