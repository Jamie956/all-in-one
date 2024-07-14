package org.example._1_ico_container._19_listener.demo1;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * ApplicationListener 的实现类监听指定事件 ApplicationEvent，当事件发生时就会执行方法 onApplicationEvent
     */
    @Test
    public void publishEventTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppListener.class);
        //初始化事件多播器入口
        context.refresh();
    }
}
