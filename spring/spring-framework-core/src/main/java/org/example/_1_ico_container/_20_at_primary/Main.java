package org.example._1_ico_container._20_at_primary;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	/**
	 * @Primary 注解方法，表示容器中有多个相同类型的对象时，getBean 时优先使用  Primary 注解的 Bean
	 */
	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();
		Assert.assertEquals(context.getBean(EmptyObject.class), context.getBean("eo2"));
	}
}
