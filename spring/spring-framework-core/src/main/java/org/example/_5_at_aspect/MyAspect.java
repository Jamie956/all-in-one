package org.example._5_at_aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyAspect {
    @Pointcut("execution(public void org.example._5_at_aspect.X.foo(..))")
    public void pointcut() {}
    @Around("pointcut()")
    public Object aroundHandle(ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("before");
        Object result = joinPoint.proceed(args);
        System.out.println("after");
        return result;
    }
}

