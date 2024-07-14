package com.jamie.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String providerOK(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + "  成功访问";
    }

    /**
     * 耗时任务消耗3秒，等待时间有5秒，服务提供者不会熔断
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "timeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String providerNoTimeOut(Integer id) {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName() + " 耗时任务成功执行";
    }

    /**
     * 耗时任务消耗3秒，而等待时间只有1秒，服务提供者会熔断
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "timeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String providerTimeOut(Integer id) {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName() + " 耗时任务成功执行";
    }

    @HystrixCommand(fallbackMethod = "exceptionHandler")
    public String providerException(Integer id) {
        int age = 10 / 0;
        return "线程池:  " + Thread.currentThread().getName() + " 任务成功执行";
    }

    public String timeOutHandler(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + "  超时了！来自服务提供者的错误提示";
    }

    public String exceptionHandler(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + "  异常了！来自服务提供者的错误提示";
    }

    /**
     * 服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试， id: " + id;
    }
}