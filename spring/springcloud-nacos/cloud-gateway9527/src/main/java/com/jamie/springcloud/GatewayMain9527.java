package com.jamie.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 代码配置路由
 * http://localhost:9527/guonei
 *
 * token 校验
 * http://localhost:9527/guonei?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b20iLCJpc3MiOiJkZGQxMjMiLCJleHAiOjE2MjgwMDY2OTYsImlhdCI6MTYyNzk3Nzg5NiwianRpIjoiMSJ9.gp96Lz4UPE3r5lrIOhMeH5XYTuNbbpaeA5TNAluIQXxoUskMULFIP93GyA6ipSffcE1RGbBACTD-U7JSm2Lp3g
 *
 * 访问注册中心的服务
 * http://localhost:9527/payment/get/1
 * http://localhost:9527/payment/discovery
 *
 * 使用 PrefixPath
 * http://localhost:9527/get/1
 *
 * 使用 StripPrefix
 * http://localhost:9527/prefix/payment/get/1
 *
 * 模拟登录
 * http://localhost:9527/auth/login
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9527.class, args);
    }
}
