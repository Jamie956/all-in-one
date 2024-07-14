package com.jamie.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务提供者自测
 *
 * 正常测试
 * http://localhost:8001/payment/hystrix/ok/1
 * 模拟不超时任务
 * http://localhost:8001/payment/hystrix/notimeout/1
 * 模拟超时任务
 * http://localhost:8001/payment/hystrix/timeout/1
 * 模拟发生异常
 * http://localhost:8001/payment/hystrix/exception/1
 * 熔断（多次异常失败时，再正确调用会暂时失败，过会才会恢复正常）
 * http://localhost:8001/payment/circuit/1
 *
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class ProviderHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderHystrixMain8001.class, args);
    }
}
