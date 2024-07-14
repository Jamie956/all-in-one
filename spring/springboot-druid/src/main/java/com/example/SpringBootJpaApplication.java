package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * druid index:
 * http://localhost:8085/druid/index.html
 */
@EnableJpaRepositories(basePackages = "com.example")
@SpringBootApplication
public class SpringBootJpaApplication{
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}
}
