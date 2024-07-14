package org.example._6_aop.xml;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class TargetAdvice implements MethodBeforeAdvice, AfterReturningAdvice, MethodInterceptor {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
    }
    @Override
    public void before(Method method, Object[] args, Object target) {
    }
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("invoke before");
        Object target = invocation.proceed();
        System.out.println("invoke after");
        return (Integer) target + 1;
    }
}