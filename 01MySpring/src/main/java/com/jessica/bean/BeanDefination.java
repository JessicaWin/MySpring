package com.jessica.bean;

import java.util.Map;

public class BeanDefination {
	private String beanId;
	private String classPath;
	private Scope scope = Scope.SINGLETON;
	private Map<String, String> properties;

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public enum Scope {
		SINGLETON("singleton"), PROTOTYPE("prototype");
		private String type;

		private Scope(String type) {
			this.type = type;
		}

		public String getType() {
			return this.type;
		}

		public void setName(String type) {
			this.type = type;
		}
	}
}
