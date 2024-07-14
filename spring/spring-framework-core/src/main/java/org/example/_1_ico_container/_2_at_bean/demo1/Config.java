package org.example._1_ico_container._2_at_bean.demo1;

import org.example.share.EmptyObject;
import org.springframework.context.annotation.Bean;

public class Config {

    @Bean
    public EmptyObject emptyObject() {
        return new EmptyObject();
    }
}
