package com.jamie.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 自测
 * http://localhost:8001/payment/get/1
 *
 * http://localhost:8001/payment/create
 * {
 *     "id": 2,
 *     "serial": "shake"
 * }
 *
 * http://localhost:8001/payment/discovery
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
