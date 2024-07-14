package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AppConfig {
    @Bean
    public X getX () {
        return new X();
    }
}
