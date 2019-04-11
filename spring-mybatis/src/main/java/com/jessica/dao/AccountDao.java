package com.jessica.dao;

import org.apache.ibatis.annotations.Param;

public interface AccountDao {
	void outMoney(@Param("name") String name, @Param("money") int money);

	void inMoney(@Param("name") String name, @Param("money") int money);
}
