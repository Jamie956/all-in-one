package org.example._1_ico_container._3_bean_definition;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;

public class Main {
    /**
     * GenericBeanDefinition 创建一个 bean definition 并注册到容器
     */
    @Test
    public void newBeanDefinition() {
        GenericApplicationContext context = new GenericApplicationContext();
        String beanName = "myBean";
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(EmptyObject.class);
        //register bean definition
        context.registerBeanDefinition(beanName, beanDefinition);
        //initial container bean
        context.refresh();
        //get bean from Cache of singleton objects
        Assert.assertNotNull(context.getBean(EmptyObject.class));
        Assert.assertNotNull(context.getBean(beanName));
    }

    /**
     * GenericBeanDefinition 创建一个 bean definition，但是 bean definition 类型是 factory bean
     * factory bean 和 FactoryBean.getObject 的返回对象都会注册到容器
     *
     */
    @Test
    public void beanFactoryAsBeanDefinition() {
        GenericApplicationContext context = new GenericApplicationContext();
        String beanName = "myBean";
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        // bean factory as bean definition
        beanDefinition.setBeanClass(MyFactoryBean.class);
        context.registerBeanDefinition(beanName, beanDefinition);
        context.refresh();

        //get bean factory not customize bean
        MyFactoryBean factoryBean = context.getBean(BeanFactory.FACTORY_BEAN_PREFIX + beanName, MyFactoryBean.class);
        Assert.assertNotNull(factoryBean);
        //debug FactoryBeanRegistrySupport.doGetObjectFromFactoryBean: get bean from bean factory
        Assert.assertNotNull(context.getBean(beanName, EmptyObject.class));
        Assert.assertNotNull(context.getBean(EmptyObject.class));
    }

}
