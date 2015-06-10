package com.wuxincheng.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

public class SpringContextUtil {
	
	private static ApplicationContext applicationContext; // Spring应用上下文环境

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}

	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		return applicationContext.getBean(requiredType);
	}

	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> requiredType) throws BeansException {
		return BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, requiredType);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> requiredType,
			boolean includeNonSingletons, boolean allowEagerInit) throws BeansException {
		return BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, requiredType,
				includeNonSingletons, allowEagerInit);
	}

	public static Resource getResource(String location) {
		return applicationContext.getResource(location);
	}

	public static Resource[] getResources(String location) throws IOException {
		return applicationContext.getResources(location);
	}

	public static Properties loadProperties(String location) throws IOException {
		Resource resource = null;
		InputStream is = null;
		try {
			resource = getResource(location);
			is = resource.getInputStream();
			Properties properties = new Properties();
			properties.load(is);
			return properties;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
}
