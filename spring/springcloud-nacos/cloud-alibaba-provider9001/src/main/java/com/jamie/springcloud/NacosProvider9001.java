package com.jamie.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * http://localhost:9001/payment/nacos/1
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProvider9001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosProvider9001.class, args);
    }
}
