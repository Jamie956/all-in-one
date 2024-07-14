package org.example._1_ico_container._4_lifecycle;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	/**
	 * 《Bean 的这一生》
	 * 对象皆始于构造
	 * 先有 bean aware，再有 bean factory aware
	 * 往大想，就到 context aware 了
	 * 容器准备好了，轮到 post processor 上场
	 * 注解 post construct，init bean 擦屁股
	 * 容器要关了，destroy 毁灭吧
	 */
	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Z.class, BeanPP.class, BeanFactoryPP.class);
		System.out.println("------------ before refresh ------------");
		context.refresh();
		System.out.println("------------ after refresh ------------");
		context.close();
	}

}
