package com.jessica.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.jessica.dao.AccountDao;

@Repository
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

	@Autowired
	public void AccountDaoImpl(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}

	@Override
	public void outMoney(String name, int money) {
		this.getJdbcTemplate().update("update account set money = money - ? where name = ?", money, name);

	}

	@Override
	public void inMoney(String name, int money) {
		this.getJdbcTemplate().update("update account set money = money + ? where name = ?", money, name);
	}

}
