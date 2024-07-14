package com.jamie.springcloud.controller;

import com.jamie.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String providerOK(@PathVariable("id") Integer id) {
        return paymentService.providerOK(id);
    }

    @GetMapping("/payment/hystrix/notimeout/{id}")
    public String providerNoTimeOut(@PathVariable("id") Integer id) {
        return paymentService.providerNoTimeOut(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String providerTimeOut(@PathVariable("id") Integer id) {
        return paymentService.providerTimeOut(id);
    }

    @GetMapping("/payment/hystrix/exception/{id}")
    public String providerException(@PathVariable("id") Integer id) {
        return paymentService.providerException(id);
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        return paymentService.paymentCircuitBreaker(id);
    }
}