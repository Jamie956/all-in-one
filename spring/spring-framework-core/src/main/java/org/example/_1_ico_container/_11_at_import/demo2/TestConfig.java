package org.example._1_ico_container._11_at_import.demo2;

import org.example.share.EmptyObject;
import org.springframework.context.annotation.Bean;

public class TestConfig {
    @Bean
    public EmptyObject getObject(){
        return new EmptyObject();
    }
}
