package org.example._1_ico_container._11_at_import.demo2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Import({TestConfig.class})
 * 1.
 * AbstractApplicationContext#refresh step invokeBeanFactoryPostProcessors
 * -> ConfigurationClassPostProcessor#processConfigBeanDefinitions
 * 第一次 invokeBeanDefinitionRegistryPostProcessors 调 ConfigurationClassPostProcessor
 * @see org.springframework.context.support.AbstractApplicationContext#refresh
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions
 * 2.
 * ConfigurationClassPostProcessor#processConfigBeanDefinitions step parser.parse
 * -> ConfigurationClassParser#processImports step processConfigurationClass
 * 将 import class TestConfig 存到 configurationClasses，而 Y 还没存
 * @see org.springframework.context.annotation.ConfigurationClassParser#processImports
 * 3.
 * ConfigurationClassPostProcessor#processConfigBeanDefinitions step this.reader.loadBeanDefinitions
 * -> ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsForConfigurationClass step registerBeanDefinitionForImportedConfigurationClass
 * -> registerBeanDefinitionForImportedConfigurationClass step this.registry.registerBeanDefinition
 * 从 configurationClasses 获取注解的class，加入 BeanDefinitionMap
 * @see org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsForConfigurationClass
 * @see org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#registerBeanDefinitionForImportedConfigurationClass
 * 4.
 * loadBeanDefinitionsForConfigurationClass step loadBeanDefinitionsForBeanMethod
 * 在TestConfig加入BeanDefinition Map后，紧接着 TestConfig 类的注解@Bean Y 加到 BeanDefinitionMap
 * 5.
 * AbstractApplicationContext#refresh step finishBeanFactoryInitialization
 * 实例化 BeanDefinitionMap 的bean
 */
@Import({TestConfig.class})
@Configuration
public class AppConfig {
}
