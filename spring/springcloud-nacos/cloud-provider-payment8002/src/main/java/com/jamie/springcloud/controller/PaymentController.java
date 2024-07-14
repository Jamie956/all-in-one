package com.jamie.springcloud.controller;

import com.jamie.springcloud.entities.CommonResult;
import com.jamie.springcloud.entities.Payment;
import com.jamie.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int ret = paymentService.create(payment);
        if (ret > 0) {
            return new CommonResult(200, "success, port: " + serverPort, ret);
        } else {
            return new CommonResult(404, "failed", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "success port: " + serverPort, payment);
        } else {
            return new CommonResult(404, "failed", null);
        }
    }
}
