package org.example._1_ico_container._11_at_import.demo3;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Import({TestImportSelector.class})
 * 1.
 * AbstractApplicationContext#refresh step invokeBeanFactoryPostProcessors
 * -> ConfigurationClassPostProcessor#processConfigBeanDefinitions
 * 第一次 invokeBeanDefinitionRegistryPostProcessors 调 ConfigurationClassPostProcessor
 * @see org.springframework.context.support.AbstractApplicationContext#refresh
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions
 * 2.
 * ConfigurationClassPostProcessor#processConfigBeanDefinitions step parser.parse
 * -> ConfigurationClassParser#processConfigurationClass step doProcessConfigurationClass
 * 入口
 * -> ConfigurationClassParser#processImports step BeanUtils.instantiateClass(candidateClass, ImportSelector.class)
 * 反射实例化 ImportSelector
 * -> ConfigurationClassParser#processImports step selector.selectImports
 * 调 ImportSelector 实现类重写的方法 selectImports，自定义selectImports返回路径将加入 BeanDefinitionMap
 * @see org.springframework.context.annotation.ConfigurationClassParser#processConfigurationClass
 * @see org.springframework.context.annotation.ConfigurationClassParser#processImports
 * 3.
 * ConfigurationClassPostProcessor#processConfigBeanDefinitions step parser.parse
 * -> ConfigurationClassParser#processConfigurationClass step this.configurationClasses.put
 * 将 import class 和 @Configuration注解类 存到 configurationClasses，每次存一个
 * 4.
 * ConfigurationClassPostProcessor#processConfigBeanDefinitions step this.reader.loadBeanDefinitions
 * -> registerBeanDefinitionForImportedConfigurationClass step this.registry.registerBeanDefinition
 * 从 configurationClasses 获取注解的class，加入 BeanDefinitionMap
 * @see org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#registerBeanDefinitionForImportedConfigurationClass
 * 5.
 * AbstractApplicationContext#refresh step finishBeanFactoryInitialization
 * 实例化 BeanDefinitionMap 的bean
 */
@Import({TestImportSelector.class})
@Configuration
public class AppConfig {
}