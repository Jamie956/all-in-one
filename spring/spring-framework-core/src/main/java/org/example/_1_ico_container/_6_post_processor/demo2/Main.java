package org.example._1_ico_container._6_post_processor.demo2;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	/**
	 * post processor 使用自定义注解注入
	 */
	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(A.class, EmptyObject.class, MyInjector.class);
		context.refresh();
		Assert.assertNotNull(context.getBean(A.class).emptyObject);
	}
}
