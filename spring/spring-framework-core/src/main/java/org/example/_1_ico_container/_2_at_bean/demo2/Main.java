package org.example._1_ico_container._2_at_bean.demo2;

import org.junit.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @Bean autowireCandidate=false 的 Bean 不能注入到其他 Bean
     */
    @Test(expected = UnsatisfiedDependencyException.class)
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.register(Y.class);
        // debug AbstractBeanDefinition.isAutowireCandidate?
        context.refresh();
    }
}
