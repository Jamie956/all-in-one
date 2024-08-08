package org.example._4_._2_custom_editor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("data-binding.xml");
        DependsOnExoticType sample = (DependsOnExoticType) cxt.getBean("sample");
        System.out.println(sample.type.name);
    }
}
