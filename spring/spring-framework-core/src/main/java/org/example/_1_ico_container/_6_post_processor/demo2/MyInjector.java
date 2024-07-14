package org.example._1_ico_container._6_post_processor.demo2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;

public class MyInjector implements BeanPostProcessor, ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// 一旦找到 Bean 的变量带自定义注解，就去容器查找对象并设给变量
		Class<?> clazz = bean.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(MyAutowired1.class)) {
				field.setAccessible(true);
				Object injectBean = applicationContext.getBean(field.getName());
				try {
					//注入
					field.set(bean, injectBean);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
