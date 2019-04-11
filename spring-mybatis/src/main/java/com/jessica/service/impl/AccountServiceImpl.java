package com.jessica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jessica.dao.AccountDao;
import com.jessica.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	@Transactional
	public void pay(String inName, String outName, int money) {
		accountDao.outMoney(outName, money);
		accountDao.inMoney(inName, money);
	}
}
