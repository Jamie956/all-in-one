package org.example._1_ico_container._4_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Z implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
	public Z() {
        System.out.println("Z constructor");
    }

	@PostConstruct
    public void myInit() {
        System.out.println("annotation @PostConstruct");
    }

	@PreDestroy
	public void myDestroy() {
		System.out.println("annotation @PreDestroy");
	}

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware.setApplicationContext");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware.setBeanFactory");
    }

	@Override
    public void setBeanName(String beanName) {
        System.out.println("BeanNameAware.setBeanName");
    }

	@Override
	public void destroy() {
		System.out.println("DisposableBean.destroy");
	}
}