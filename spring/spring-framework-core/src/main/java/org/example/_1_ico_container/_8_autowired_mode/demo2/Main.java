package org.example._1_ico_container._8_autowired_mode.demo2;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	// byName 装配，同一类型的I 有两个实现类，不能自动决定注入哪一个，因为是按 name 注入，所以不会有冲突
	@Test
	public void byNameTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(A.class, I1.class, I2.class);
		AbstractBeanDefinition bd = (AbstractBeanDefinition) context.getBeanDefinition("a");
		bd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		context.refresh();

		Assert.assertNotNull(context.getBean(A.class));
		Assert.assertNull(context.getBean(A.class).i);
		Assert.assertNotNull(context.getBean(I1.class));
		Assert.assertNotNull(context.getBean(I2.class));
	}

	// byType 装配，同一类型的I 有两个实现类，按 type 注入会抛出异常，因为两个同类型同时注入会冲突
	@Test(expected = UnsatisfiedDependencyException.class)
	public void byTypeTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(A.class, I1.class, I2.class);
		AbstractBeanDefinition bd = (AbstractBeanDefinition) context.getBeanDefinition("a");
		bd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		context.refresh();
	}
}
