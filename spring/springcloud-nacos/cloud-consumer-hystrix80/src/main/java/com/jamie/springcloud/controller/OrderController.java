package com.jamie.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.jamie.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "globalFallBack")
public class OrderController {
    @Resource
    private PaymentService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String ok(@PathVariable("id") Integer id) {
        return paymentHystrixService.providerOK(id);
    }

    @GetMapping("/consumer/payment/hystrix/notimeout/{id}")
    @HystrixCommand(fallbackMethod = "timeOutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String consumerTimeOut(@PathVariable("id") Integer id) {
        return paymentHystrixService.providerNoTimeOut(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "timeOutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    })
    public String providerTimeOut(@PathVariable("id") Integer id) {
        return paymentHystrixService.providerTimeOut(id);
    }

    @GetMapping("/consumer/payment/hystrix/exception/{id}")
    @HystrixCommand(fallbackMethod = "exceptionFallback")
    public String consumerException(@PathVariable("id") Integer id) {
        int age = 10 / 0;
        return paymentHystrixService.providerOK(id);
    }

    @GetMapping("/consumer/payment/hystrix/pexception/{id}")
    @HystrixCommand(fallbackMethod = "exceptionFallback")
    public String providerException(@PathVariable("id") Integer id) {
        return paymentHystrixService.providerException(id);
    }

    @GetMapping("/consumer/payment/hystrix/global/{id}")
    @HystrixCommand
    public String global(@PathVariable("id") Integer id) {
        int age = 10 / 0;
        return paymentHystrixService.providerOK(id);
    }

    public String timeOutFallback(@PathVariable("id") Integer id) {
        return "超时了！来自服务消费者的错误提示";
    }

    public String exceptionFallback(@PathVariable("id") Integer id) {
        return "异常了！来自服务消费者的错误提示";
    }

    public String globalFallBack() {
        return "Global异常处理信息，请稍后再试";
    }
}
