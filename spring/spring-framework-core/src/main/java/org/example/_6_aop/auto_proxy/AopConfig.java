package org.example._6_aop.auto_proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;

public class AopConfig {
	@Bean
	public MethodInterceptor myAroundAdvice() {
		//环绕增强
		return new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("before");
				Object result = invocation.proceed();
				System.out.println("after");
				return (Integer) result + 1;
			}
		};
	}

	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
		//point cut bean
		creator.setBeanNames("a*");
		//advice bean
		creator.setInterceptorNames("myAroundAdvice");
		creator.setProxyTargetClass(true);
		return creator;
	}
}
