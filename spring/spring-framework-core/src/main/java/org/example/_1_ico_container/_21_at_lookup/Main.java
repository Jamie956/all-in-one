package org.example._1_ico_container._21_at_lookup;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	/**
	 * @Loockup value 是 Bean string name，注解的方法的返回值就是容器的 Bean 实例
	 */
	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(A.class, EmptyObject.class);
		context.refresh();
		Assert.assertNotNull(context.getBean(A.class));
		Assert.assertNotNull(context.getBean(A.class).lookupEmptyObject());
		Assert.assertTrue(context.getBean(A.class).lookupEmptyObject() instanceof EmptyObject);
	}
}
