package org.example._1_ico_container._4_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPP implements BeanPostProcessor {

	/**
	 * CustomBeanPostProcessor.postProcessBeforeInitialization
	 *
	 * 1.refresh 实例化bean 入口
	 * AbstractApplicationContext#refresh step finishBeanFactoryInitialization
	 *
	 * 2.回调入口
	 * AbstractAutowireCapableBeanFactory#doCreateBean step initializeBean
	 *
	 * 3.遍历后置处理器，BeanPostProcessor 回调
	 * AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization step processor.postProcessBeforeInitialization
	 *
	 * @see org.springframework.context.support.AbstractApplicationContext#refresh
	 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean
	 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization
	 */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor.postProcessBeforeInitialization");
        return bean;
    }

	/**
	 * CustomBeanPostProcessor.postProcessAfterInitialization
	 *
	 * 1.refresh 实例化bean 入口
	 * AbstractApplicationContext#refresh step finishBeanFactoryInitialization
	 *
	 * 2.回调入口
	 * AbstractAutowireCapableBeanFactory#doCreateBean step initializeBean
	 *
	 * 3.遍历后置处理器，BeanPostProcessor 回调
	 * AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization step processor.postProcessAfterInitialization
	 *
	 * @see org.springframework.context.support.AbstractApplicationContext#refresh
	 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean
	 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization
	 */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor.postProcessAfterInitialization");
        return bean;
    }

}