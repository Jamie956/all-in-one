package com.jamie.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 消费者，整合ribbon带负载均衡
 * http://localhost:83/consumer/payment/nacos/3
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderNacos83 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderNacos83.class, args);
    }
}
