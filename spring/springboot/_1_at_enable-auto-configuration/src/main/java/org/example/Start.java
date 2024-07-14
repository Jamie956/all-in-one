package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * @EnableAutoConfiguration
 *      @AutoConfigurationPackage
 *          -> @Import(AutoConfigurationPackages.Registrar.class): register beans
 *      @Import(AutoConfigurationImportSelector.class)
 *          -> register spring boot XXXAutoConfiguration beans
 */
@EnableAutoConfiguration
public class Start {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        //1.construct SpringApplication
        //1.1 confirm webApplicationType
        //1.2 load Factories
        //      spring-boot-2.4.13.jar!/META-INF/spring.factories
        //      spring-boot-autoconfigure-2.4.13.jar!/META-INF/spring.factories
        //      spring-beans-5.3.13.jar!/META-INF/spring.factories
        //1.3 Instance beans
        //      ApplicationContextInitializer
        //      ApplicationListener
        //2.SpringApplication run
        //2.1 prepareEnvironment: load system properties and servlet properties
        //2.2 createApplicationContext -> AnnotationConfigServletWebServerApplicationContext
        //2.3 refreshContext: ServletWebServerApplicationContext#refresh
        //2.3.1 invokeBeanFactoryPostProcessors: create @Import instances (debug AutoConfigurationImportSelector.AutoConfigurationGroup.selectImports)
        //2.3.2 registerBeanPostProcessors
        //2.3.3 onRefresh: -> createWebServer -> create tomcat (debug Tomcat Construct)
        SpringApplication.run(Start.class, args);
    }

}