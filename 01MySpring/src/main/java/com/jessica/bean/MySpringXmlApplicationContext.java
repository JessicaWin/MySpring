package com.jessica.bean;

import java.lang.reflect.Field;
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

public class MySpringXmlApplicationContext {
	private Logger logger = LogManager.getLogger(this.getClass());
	private String configFilePath;
	private Map<String, BeanDefination> beanDefinationMap;
	private Map<String, Object> beanMap;

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
			Class instanceClass = Class.forName(beanDefination.getClassPath());
			Object instance = instanceClass.newInstance();

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
							logger.debug("Init field:" + fieldName +"="+ objectMapper.convertValue(value, fieldType) +" for bean " + beanDefination.getBeanId());
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("Can not init field:" + fieldName);
						}
						break;
					}
				}
			});
			beanMap.put(beanId, instance);
			return instance;
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
				NamedNodeMap attrs = bean.getAttributes();
				BeanDefination beanDefination = new BeanDefination();
				for (int attrIndex = 0; attrIndex < attrs.getLength(); attrIndex++) {
					Node attributeNode = attrs.item(attrIndex);
					if (attributeNode.getNodeName().equals("id")) {
						beanDefination.setBeanId(attributeNode.getNodeValue());
					} else if (attributeNode.getNodeName().equals("class")) {
						beanDefination.setClassPath(attributeNode.getNodeValue());
					} else if (attributeNode.getNodeName().equals("scope")) {
						beanDefination.setScope(
								Scope.SINGLETON.getType().equals(attributeNode.getNodeValue()) ? Scope.SINGLETON
										: Scope.PROTOTYPE);
					}
				}
				logger.debug("Init bean defination for " + beanDefination.getBeanId());
				logger.debug("Init properties for bean " + beanDefination.getBeanId());
				NodeList properNodeList = bean.getChildNodes();
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
							logger.debug("Add property for " + beanDefination.getBeanId() + ":(" + filedName + ","
									+ fieldValue + ")");
							properties.put(filedName, fieldValue);
						} else {
							logger.error("property is not right");
							throw new Exception("property is not right");
						}
					}
				}
				beanDefination.setProperties(properties);
				beanDefinationMap.put(beanDefination.getBeanId(), beanDefination);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
	}
}
