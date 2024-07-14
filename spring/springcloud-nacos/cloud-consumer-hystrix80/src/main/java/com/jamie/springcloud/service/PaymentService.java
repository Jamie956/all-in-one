package com.jamie.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
//在服务调用接口实现降级统一处理
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT" ,fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping("/payment/hystrix/ok/{id}")
    String providerOK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/notimeout/{id}")
    String providerNoTimeOut(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String providerTimeOut(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/exception/{id}")
    String providerException(@PathVariable("id") Integer id);
}