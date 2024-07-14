package org.example._1_ico_container._12_circular_references;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单例的setter注入：spring 解决的循环依赖
 * 一级缓存：singletonObjects，存放完全实例化属性赋值完成的Bean，直接可以使用
 * 二级缓存：earlySingletonObjects，存放早期Bean的引用，尚未属性装配的Bean
 * 三级缓存：singletonFactories，三级缓存，存放实例化完成的Bean工厂
 */
public class CRTest {
	/**
	 * TestService1 -> 从一级缓存取不到实例 debug AbstractBeanFactory#doGetBean getSingleton
	 * -> 创建实例 createBean -> 提前暴露，添加到三级缓存 condition debug DefaultSingletonBeanRegistry#addSingletonFactory
	 * -> 依赖注入 -> TestService2 -> 从一级缓存取不到实例 -> 创建实例 -> 提前暴露，添加到三级缓存
	 * -> 依赖注入 -> TestService1 -> 从三级缓存获取实例，并添加到二级缓存 DefaultSingletonBeanRegistry#getSingleton
	 * -> TestService2 依赖注入成功 -> TestService2 初始化完成 -> TestService2 添加到一级缓存 DefaultSingletonBeanRegistry.addSingleton
	 * -> TestService1依赖注入成功 -> TestService1 初始化完成 -> TestService1 添加到一级缓存
	 */
	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("org.example._1_ico_container._12_circular_references.demo1");
		context.refresh();

		Assert.assertNotNull(context.getBean(TestService1.class));
		Assert.assertNotNull(context.getBean(TestService2.class));
	}
}
