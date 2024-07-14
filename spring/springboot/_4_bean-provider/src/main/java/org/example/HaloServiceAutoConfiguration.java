package org.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(HaloService.class)
@EnableConfigurationProperties(HaloProperties.class)
public class HaloServiceAutoConfiguration {
    // no need create new HaloService instance again if config in spring.factories
//    @Bean
//    public HaloService haloService() {
//        return new HaloService();
//    }
}
