package org.example._1_ico_container._11_at_import.demo1;

import org.example.share.EmptyObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Import({EmptyObject.class})
 * 1.
 * AbstractApplicationContext#refresh step invokeBeanFactoryPostProcessors
 * -> ConfigurationClassPostProcessor#processConfigBeanDefinitions
 * 第一次 invokeBeanDefinitionRegistryPostProcessors 调 ConfigurationClassPostProcessor
 * @see org.springframework.context.support.AbstractApplicationContext#refresh
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions
 * 2.
 * ConfigurationClassPostProcessor#processConfigBeanDefinitions step parser.parse
 * -> ConfigurationClassParser#processImports step processConfigurationClass
 * 将 import class X 存到 configurationClasses
 * @see org.springframework.context.annotation.ConfigurationClassParser#processImports
 * 3.
 * ConfigurationClassPostProcessor#processConfigBeanDefinitions step this.reader.loadBeanDefinitions
 * -> registerBeanDefinitionForImportedConfigurationClass step this.registry.registerBeanDefinition
 * 从 configurationClasses 获取注解的class，加入 BeanDefinitionMap
 * @see org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#registerBeanDefinitionForImportedConfigurationClass
 * 4.
 * AbstractApplicationContext#refresh step finishBeanFactoryInitialization
 * 实例化 BeanDefinitionMap 的bean
 */
@Import({EmptyObject.class})
@Configuration
public class AppConfig {
}