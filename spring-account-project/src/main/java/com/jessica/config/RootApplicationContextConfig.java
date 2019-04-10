package com.jessica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "com.jessica.service", "com.jessica.dao", "com.jessica.controller" })
@EnableTransactionManagement
public class RootApplicationContextConfig {
//	@Bean(name = "dataSource")
//	public ComboPooledDataSource dataSource() throws Exception {
//		ComboPooledDataSource dataSource = new ComboPooledDataSource();
//		dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
//		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/java");
//		dataSource.setUser("root");
//		dataSource.setPassword("1121mysql");
//		return dataSource;
//	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/java");
		dataSource.setUsername("root");
		dataSource.setPassword("1121mysql");
		return dataSource;

	}

	@Bean
	public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

}
