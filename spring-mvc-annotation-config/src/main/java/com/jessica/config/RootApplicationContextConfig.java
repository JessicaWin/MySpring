package com.jessica.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "com.jessica.service", "com.jessica.dao" })
@EnableTransactionManagement
@MapperScan({ "com.jessica.dao" })
public class RootApplicationContextConfig {

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

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DriverManagerDataSource dataSource) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.jessica.entity");
		return sqlSessionFactoryBean;
	}

	// can be replaced by @MapperScan
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer() {
//		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//		configurer.setBasePackage("com.jessica.dao");
//		return configurer;
//	}

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DriverManagerDataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
