package org.example._1_ico_container._11_at_import.demo4;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Import({TestImportBeanDefinitionRegistrar.class})
 * 1.
 * AbstractApplicationContext#refresh step invokeBeanFactoryPostProcessors
 * -> ConfigurationClassPostProcessor#processConfigBeanDefinitions
 * 第一次 invokeBeanDefinitionRegistryPostProcessors 调 ConfigurationClassPostProcessor
 * @see org.springframework.context.support.AbstractApplicationContext#refresh
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions
 * 2.
 * ConfigurationClassPostProcessor#processConfigBeanDefinitions step parser.parse
 * -> ConfigurationClassParser#processImports step BeanUtils.instantiateClass(candidateClass, ImportBeanDefinitionRegistrar.class)
 * 反射实例化 自定义 TestImportBeanDefinitionRegistrar
 * -> ConfigurationClassParser#processImports step configClass.addImportBeanDefinitionRegistrar
 * 自定义 ImportBeanDefinitionRegistrar 添加到 ConfigurationClass.importBeanDefinitionRegistrars
 * @see org.springframework.context.annotation.ConfigurationClass.importBeanDefinitionRegistrars
 * 3.
 * ConfigurationClassPostProcessor#processConfigBeanDefinitions step this.reader.loadBeanDefinitions
 * -> ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsFromRegistrars
 * 从 importBeanDefinitionRegistrars 获取的实例 调 ImportBeanDefinitionRegistrar#registerBeanDefinitions
 * @see org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsFromRegistrars
 * 4.
 * TestImportBeanDefinitionRegistrar#registerBeanDefinitions
 * 自定义 import的实现方法 registerBeanDefinitions 将 bean 加入 BeanDefinitionMap
 * 5.
 * AbstractApplicationContext#refresh step finishBeanFactoryInitialization
 * 实例化 BeanDefinitionMap 的bean
 */
@Import({TestImportBeanDefinitionRegistrar.class})
@Configuration
public class AppConfig {
}