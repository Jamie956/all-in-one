package org.example._1_ico_container._8_autowired_mode.demo1;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	/**
	 * AbstractBeanDefinition.setAutowireMode 表示 Bean setter 能够注入容器 Bean
	 */
	// debug PropertyValues pvs = (mbd.hasPropertyValues() ? mbd.getPropertyValues() : null);
	@Test
	public void autoInjectTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(A.class, EmptyObject.class);

		AbstractBeanDefinition bd = (AbstractBeanDefinition) context.getBeanDefinition("a");
		bd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		context.refresh();

		Assert.assertNotNull(context.getBean(A.class));
		Assert.assertNotNull(context.getBean(A.class).o);
	}
}