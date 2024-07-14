# env
jdk 8
springboot 2.4.13

# first demo document
https://docs.spring.io/spring-boot/docs/2.4.13/reference/html/getting-started.html#getting-started-first-application

# pom
deploy springboot executable Jar
add plugin
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```
mvn package
java -jar target/myproject-0.0.1-SNAPSHOT.jar

dependency yml配置提示
spring-boot-configuration-processor

# annotation
return proxy bean if proxyBeanMethods true
@Configuration(proxyBeanMethods = false)

register beans
@EnableConfigurationProperties(ServerProperties.class)

assign prefix properties to bean properties
@ConfigurationProperties(prefix="person")

# auto config
DispatcherServletAutoConfiguration

true if OnWebApplicationCondition match and type match
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)

must exist class
@ConditionalOnClass(CharacterEncodingFilter.class)

@ConditionalOnProperty(prefix = "server.servlet.encoding", value = "enabled", matchIfMissing = true)

true if missing bean
@ConditionalOnMissingBean

# debug mode
application.properties
debug=true

# yml properties defined
person:
    单引号转义，双引号不转义
    user: "tom \n tom"
    list:
        - a1
        - a2
    list2: [aa,bb]
    obj: {k1:v1,k2:v2}

# mvc
http request method (get, post, delete, put) filter
HiddenHttpMethodFilter

parse controller method param
InvocableHandlerMethod


