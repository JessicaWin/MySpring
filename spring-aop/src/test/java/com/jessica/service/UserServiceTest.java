package com.jessica.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jessica.entity.Dept;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springConfig.xml")
//@ContextConfiguration(classes = { Config.class })
public class UserServiceTest {
	private Log logger = LogFactory.getLog(UserServiceTest.class);
	@Autowired
	private UserService userService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void testUpdate() {
		userService.updateUser("id", "test");
	}

	@Test
	public void testInsert() {
		userService.insertUser("test");
	}

	@Test
	public void testJdbc() {
		// 创建连接池，先使用Spring框架内置的连接池
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/java");
		dataSource.setUsername("root");
		dataSource.setPassword("1121mysql");
		// 创建模板类
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// 完成数据的添加
		jdbcTemplate.update("insert into dept values (null,?,?)", "market", "shanghai");
	}

	@Test
	public void testInsertDept() {
		// 完成数据的添加
		this.jdbcTemplate.update("insert into dept values (null,?,?)", "Develop", "shanghai");
	}

	@Test
	// 修改操作
	public void testUpdateDept() {
		jdbcTemplate.update("update dept set depName=?,loc =? where depNo = ?", "test", "test", 30);
	}

	@Test
	// 删除操作
	public void testDelete() {
		jdbcTemplate.update("delete from dept where depNo = ?", 5);
	}

	@Test
	// 查询一条记录
	public void testSelectOne() {
		Dept dept = jdbcTemplate.queryForObject("select * from dept where depNo = ?", new BeanMapper(), 30);
		logger.debug(dept);
		System.out.println(dept.getDepName());
	}

	@Test
	// 查询所有记录
	public void testSelectAll() {
		List<Dept> list = jdbcTemplate.query("select * from dept", new BeanMapper());
		for (Dept dept : list) {
			System.out.println(dept);
		}
	}
}

class BeanMapper implements RowMapper<Dept> {
	@Override
	public Dept mapRow(ResultSet rs, int arg1) throws SQLException {
		Dept dept = new Dept();
		dept.setDepNo(rs.getInt("depNo"));
		dept.setDepName(rs.getString("depName"));
		dept.setLoc(rs.getString("loc"));
		return dept;
	}
}