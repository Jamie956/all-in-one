package org.example._1_ico_container._4_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanFactoryPP implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("Excuting BeanFactoryPostProcessor.postProcessBeanFactory");
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
	}
}