package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfigWithProfile {

    @Profile("dev")
    @Bean
    public X getX () {
        return new X();
    }

    @Profile("test")
    @Bean
    public Y getY () {
        return new Y();
    }
}
