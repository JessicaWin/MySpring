package com.jessica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//定义Spring MVC扫描的包
@ComponentScan({ "com.jessica" })
//启动Spring MVC配置
public class WebServletConfig {
	/***
	 * 通过注解 @Bean 初始化视图解析器
	 * 
	 * @return ViewResolver 视图解析器
	 */
	@Bean(name = "internalResourceViewResolver")
	public ViewResolver initViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
