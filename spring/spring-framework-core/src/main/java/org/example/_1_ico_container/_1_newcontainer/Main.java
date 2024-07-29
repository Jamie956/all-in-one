package org.example._1_ico_container._1_newcontainer;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    /**
     * AnnotationConfigApplicationContext: Spring container，loading beans into container via scan annotation within package
     *
     * new AnnotationConfigApplicationContext():
     * 1.父类创建 beanFactory，存放 bean definition
     * @see org.springframework.context.support.GenericApplicationContext#GenericApplicationContext()
     * 2.注册 processor
     * @see org.springframework.context.annotation.AnnotationConfigUtils#registerAnnotationConfigProcessors(BeanDefinitionRegistry, Object)
     * processors put into bean definition map
     * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#registerBeanDefinition
     *
     * context.scan(): 扫描包路径下的注解类, 将扫描的类 bean 注册到 BeanDefinitionMap
     * 1.doScan step findCandidateComponents: scan path
     * 2.doScan step registerBeanDefinition: BeanDefinition put into BeanDefinitionMap
     * @see org.springframework.context.annotation.ClassPathBeanDefinitionScanner#doScan
     *
     * context.refresh(): create beans
     */
    @Test
    public void annotationConfigContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.example._1_ico_container._1_newcontainer");
        context.refresh();
        Assert.assertNotNull(context.getBean(X.class));
    }

    /**
     * ClassPathXmlApplicationContext 创建容器，容器按照 XML 声明创建 Beans
     */
    @Test
    public void refreshBeansByXml() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc-bean.xml");
        Assert.assertNotNull(context.getBean(EmptyObject.class));
    }

}
