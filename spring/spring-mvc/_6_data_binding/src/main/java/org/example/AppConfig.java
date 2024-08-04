package org.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//no http request data binding to module object if no this annotation EnableWebMvc
@EnableWebMvc
public class AppConfig {
}
