package org.example._1_ico_container._6_post_processor.demo1;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
	/**
	 * Post Processors 表示Bean 初始化后执行的一些处理器，处理器的方法入参各种各样
	 * BeanFactoryPostProcessor.postProcessBeanFactory 的方法入参就是 bean factory，可以修改 bean definition
	 */
	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(BeanFactoryPP.class, EmptyObject.class);
		context.refresh();
		Assert.assertNotNull(context.getBean(X.class));
	}
}
