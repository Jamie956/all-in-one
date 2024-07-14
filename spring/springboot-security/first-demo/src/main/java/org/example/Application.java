package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
start web application
request controller data: http://localhost:8080/hi
redirect to login page: http://localhost:8080/login
input login username and password:
username: user
password: print in console
request again: http://localhost:8080/hi
response message
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
