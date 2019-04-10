package com.jessica.xml.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jessica.dao.AccountDao;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
	@Override
	public void outMoney(String name, int money) {
		this.getJdbcTemplate().update("update account set money = money - ? where name = ?", money, name);

	}

	@Override
	public void inMoney(String name, int money) {
		this.getJdbcTemplate().update("update account set money = money + ? where name = ?", money, name);
	}

}
