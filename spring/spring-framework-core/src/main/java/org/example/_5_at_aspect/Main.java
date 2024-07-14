package org.example._5_at_aspect;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @PointCut value 是匹配方法和类的表达式，表示哪些方法和类会被拦截
     * @Around 注解方法，方法是实现拦截处理逻辑的地方
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class, MyAspect.class, X.class);
        context.refresh();
        context.getBean(X.class).foo();
    }
}