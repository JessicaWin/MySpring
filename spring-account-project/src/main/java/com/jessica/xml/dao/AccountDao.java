package com.jessica.xml.dao;

public interface AccountDao {
	void outMoney(String name, int money);

	void inMoney(String name, int money);
}
