# Run Spring MVC application in Tomcat
1. download tomcat 8.5.84

2. pom.xml update
    
    dependency
    ```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.1</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope>
    </dependency>
    ```
    
    packaging
    ```xml
    <packaging>war</packaging>
    ```

3. src/main/webapp/WEB-INF/web.xml
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
             version="4.0">
        <servlet>
            <servlet-name>SpringMVC</servlet-name>
            <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
            <init-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>classpath:springMVC.xml</param-value>
            </init-param>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>SpringMVC</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>
    </web-app>
    ```

4. src/main/resources/springMVC.xml
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd">
    
        <context:component-scan base-package="org.example"/>
    </beans>
    ```

5. idea start application: 
    1. project structure -> modules -> add web
    2. Web Resource Directory: path\to\webapp
    3. Development Descriptor: path\to\web.xml

4. start application: 

    run/debug configurations -> tomcat -> deployment -> + artifact 
    
    -> project:war exploded -> application context: /



## server logs 中文乱码

1. apache-tomcat-8.5.84\conf\logging.properties
java.util.logging.ConsoleHandler.encoding = UTF-8
->
java.util.logging.ConsoleHandler.encoding = GBK

2. or vm options: -Dfile.encoding=UTF-8
