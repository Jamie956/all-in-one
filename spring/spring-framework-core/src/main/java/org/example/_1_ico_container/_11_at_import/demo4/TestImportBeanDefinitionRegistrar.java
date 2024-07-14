package org.example._1_ico_container._11_at_import.demo4;

import org.example.share.EmptyObject;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class TestImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// debug
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(EmptyObject.class);
		registry.registerBeanDefinition("emptyObject", rootBeanDefinition);
	}
}