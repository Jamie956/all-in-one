package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "eureka-register-service")
public interface ConsumerService {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String hi();
}
