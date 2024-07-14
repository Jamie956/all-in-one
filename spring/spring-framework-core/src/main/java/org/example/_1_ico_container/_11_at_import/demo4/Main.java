package org.example._1_ico_container._11_at_import.demo4;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	/**
	 * @Import value 是 ImportBeanDefinitionRegistrar 实现类时，
	 * ImportBeanDefinitionRegistrar.registerBeanDefinitions 能够注册 bean definition
	 */
	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();
		Assert.assertNotNull(context.getBean(EmptyObject.class));
	}
}
