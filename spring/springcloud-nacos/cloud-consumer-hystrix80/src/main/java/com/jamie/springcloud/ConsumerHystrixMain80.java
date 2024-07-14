package com.jamie.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 正常调用
 * http://localhost/consumer/payment/hystrix/ok/1
 * 服务消费者自己超时
 * http://localhost/consumer/payment/hystrix/notimeout/1
 * 服务提供者超时
 * http://localhost/consumer/payment/hystrix/timeout/1
 * 服务消费者自己发生异常
 * http://localhost/consumer/payment/hystrix/exception/1
 * 服务提供者发生异常
 * http://localhost/consumer/payment/hystrix/pexception/1
 * 使用全局错误提示
 * http://localhost/consumer/payment/hystrix/global/1
 * 服务调用接口统一处理（关闭服务提供者测试）
 * http://localhost/consumer/payment/hystrix/ok/1
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class ConsumerHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixMain80.class, args);
    }
}
