package org.example._1_ico_container._7_at_component_scan.demo2;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	/**
	 *  @ComponentScan includeFilters 表示还能扫描自定义注解，并注册 beans 到容器
	 */
	@Test
	public void test() {
		//register internal post processor bean definition
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//register annotated config class
		context.register(ConfigIncludeFilters.class);
		// invokeBeanFactoryPostProcessors()
		// -> ConfigurationClassParser.doProcessConfigurationClass(..): parse @ComponentScan
		// -> ComponentScanAnnotationParser.parse(..): parse @ComponentScan include filter and doScan()
		context.refresh();
		TestCase.assertNotNull(context.getBean(X.class));
	}
}
