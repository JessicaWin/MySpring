package com.jessica.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jessica.bean.BeanDefination.Scope;
import com.jessica.processor.impl.BeanPostProcessor;

public class MySpringXmlApplicationContext {
	private Logger logger = LogManager.getLogger(this.getClass());
	private String configFilePath;
	private Map<String, BeanDefination> beanDefinationMap;
	private Map<String, Object> beanMap;
	private BeanPostProcessor beanPostProcessor;

	public MySpringXmlApplicationContext(String configFilePath) {
		this.configFilePath = configFilePath;
		this.beanMap = new HashMap<String, Object>();
	}

	public Object getBean(String beanId) throws Exception {
		if (beanDefinationMap == null) {
			this.initBeanDefinations();
		}
		BeanDefination beanDefination = this.beanDefinationMap.get(beanId);
		if (beanDefination == null) {
			logger.error("No bean is defined in the config file: " + beanId);
			throw new Exception("No bean is defined in the config file: " + beanId);
		}
		if (beanMap.get(beanId) != null && beanDefination.getScope().equals(Scope.SINGLETON)) {
			return beanMap.get(beanId);
		} else {
			logger.debug("Create instance for " + beanDefination.getBeanId());
			if (beanDefination.getFactoryBean() != null) {
				return this.createInstanceFromFactory(beanDefination);
			} else {
				return this.createBeanInstance(beanDefination);
			}
		}
	}

	private void initBeanDefinations() {
		if (StringUtils.isEmpty(this.configFilePath)) {
			logger.error("Config file path is not set.");
			return;
		}
		this.beanDefinationMap = new HashMap<String, BeanDefination>();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(this.configFilePath);
			NodeList beanList = document.getElementsByTagName("bean");
			logger.debug("There are " + beanList.getLength() + "beans");
			for (int i = 0; i < beanList.getLength(); i++) {
				Node bean = beanList.item(i);
				BeanDefination beanDefination = this.createBeanDefination(bean);
				logger.debug("Init bean defination for " + beanDefination.getBeanId());
				logger.debug("Init properties for bean " + beanDefination.getBeanId());
				beanDefination.setProperties(this.getBeanProperties(bean));
				beanDefinationMap.put(beanDefination.getBeanId(), beanDefination);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
	}

	private Object createInstanceFromFactory(BeanDefination beanDefination) throws Exception {
		Object factory = this.getBean(beanDefination.getFactoryBean());
		if (factory == null) {
			logger.error("Can not find bean factory: " + beanDefination.getFactoryBean());
			throw new Exception("Can not find bean factory: " + beanDefination.getFactoryBean());
		}
		Class factoryClass = factory.getClass();
		Method method = factoryClass.getDeclaredMethod(beanDefination.getFactroyMethod(), null);
		if (method == null) {
			logger.error("Can not find method in calss: " + beanDefination.getFactroyMethod());
			throw new Exception("Can not find method in calss: " + beanDefination.getFactroyMethod());
		}

		Object instance = method.invoke(factory, null);
		if (this.beanPostProcessor != null) {
			instance = this.beanPostProcessor.postProcessBeforeInitialization(instance, beanDefination.getBeanId());
		}
		if (this.beanPostProcessor != null) {
			instance = this.beanPostProcessor.postProcessAfterInitialization(instance, beanDefination.getBeanId());
		}
		beanMap.put(beanDefination.getBeanId(), instance);
		return instance;
	}

	private Object createBeanInstance(BeanDefination beanDefination) throws Exception {
		Class instanceClass = Class.forName(beanDefination.getClassPath());
		Object instance = instanceClass.newInstance();
		Object newInstance = null;
		if (!(instance instanceof BeanPostProcessor)) {
			if (this.beanPostProcessor != null) {
				newInstance = this.beanPostProcessor.postProcessBeforeInitialization(instance,
						beanDefination.getBeanId());
			}
			Field[] fields = instanceClass.getDeclaredFields();
			beanDefination.getProperties().forEach((fieldName, value) -> {
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);
					if (field.getName().equals(fieldName)) {
						Class fieldType = field.getType();
						ObjectMapper objectMapper = new ObjectMapper();
						try {
							field.set(instance, objectMapper.convertValue(value, fieldType));
							logger.debug("Init field:" + fieldName + "=" + objectMapper.convertValue(value, fieldType)
									+ " for bean " + beanDefination.getBeanId());
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("Can not init field:" + fieldName);
						}
						break;
					}
				}
			});
		}
		if (this.beanPostProcessor != null) {
			newInstance = this.beanPostProcessor.postProcessAfterInitialization(instance, beanDefination.getBeanId());
		}
		if (newInstance != null) {
			beanMap.put(beanDefination.getBeanId(), newInstance);
			return newInstance;
		} else {
			beanMap.put(beanDefination.getBeanId(), instance);
			return instance;
		}
	}

	private BeanDefination createBeanDefination(Node beanNode) throws Exception {
		NamedNodeMap attrs = beanNode.getAttributes();
		BeanDefination beanDefination = new BeanDefination();
		for (int attrIndex = 0; attrIndex < attrs.getLength(); attrIndex++) {
			Node attributeNode = attrs.item(attrIndex);
			if (attributeNode.getNodeName().equals("id")) {
				beanDefination.setBeanId(attributeNode.getNodeValue());
			} else if (attributeNode.getNodeName().equals("class")) {
				beanDefination.setClassPath(attributeNode.getNodeValue());
				Class instanceClass = Class.forName(beanDefination.getClassPath());
				Object instance = instanceClass.newInstance();
				if (instance instanceof BeanPostProcessor) {
					this.beanPostProcessor = (BeanPostProcessor) instance;
				}
				beanMap.put(beanDefination.getBeanId(), instance);
			} else if (attributeNode.getNodeName().equals("scope")) {
				beanDefination.setScope(Scope.SINGLETON.getType().equals(attributeNode.getNodeValue()) ? Scope.SINGLETON
						: Scope.PROTOTYPE);
			} else if (attributeNode.getNodeName().equals("factory-bean")) {
				beanDefination.setFactoryBean(attributeNode.getNodeValue());
			} else if (attributeNode.getNodeName().equals("factory-method")) {
				beanDefination.setFactroyMethod(attributeNode.getNodeValue());
			}
		}
		return beanDefination;
	}

	private Map<String, String> getBeanProperties(Node beanNode) throws Exception {
		NodeList properNodeList = beanNode.getChildNodes();
		Map<String, String> properties = new HashMap<String, String>();
		for (int properIndex = 0; properIndex < properNodeList.getLength(); properIndex++) {
			Node property = properNodeList.item(properIndex);
			if (property.getNodeName().equals("property")) {
				NamedNodeMap properAttrs = property.getAttributes();
				String filedName = null, fieldValue = null;
				for (int attrIndex = 0; attrIndex < properAttrs.getLength(); attrIndex++) {
					Node attributeNode = properAttrs.item(attrIndex);
					if (attributeNode.getNodeName().equals("name")) {
						filedName = attributeNode.getNodeValue();
					} else if (attributeNode.getNodeName().equals("value")) {
						fieldValue = attributeNode.getNodeValue();
					}
				}
				if (filedName != null && fieldValue != null) {
					properties.put(filedName, fieldValue);
				} else {
					logger.error("property is not right");
					throw new Exception("property is not right");
				}
			}
		}
		return properties;
	}
}
