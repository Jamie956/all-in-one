package org.example._1_ico_container._6_post_processor.demo1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class BeanFactoryPP implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("Excuting BeanFactoryPostProcessor.postProcessBeanFactory");
		//在这里加一个 bean definition
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(X.class);
		((DefaultListableBeanFactory)beanFactory).registerBeanDefinition("x", beanDefinition);
	}
}