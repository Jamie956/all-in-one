# Quickly start



## tomcat version
tomcat 8.5.84
javax.servlet-api scope is provided, servlet-api.jar already provide by tomcat lib

do not use tomcat 10
tomcat 10 return 404 error, servlet-api.jar no longer provide by tomcat lib

## pom setting

`<packaging>war</packaging>`



## idea setting - web config location
project structure -> modules -> add web
-> Web Resource Directory: path\to\project\src\main\webapp
-> Development Descriptor: path\to\project\src\main\webapp\WEB-INF\web.xml

## idea setting - project run in tomcat
run/debug configurations -> application server (tomcat home) -> deployment -> + artifact -> xx:war exploded -> change application context



# server logs 中文乱码

(1)
apache-tomcat-8.5.84\conf\logging.properties
java.util.logging.ConsoleHandler.encoding = UTF-8
->
java.util.logging.ConsoleHandler.encoding = GBK

or (2)
vm options: -Dfile.encoding=UTF-8




# springMVC importance class
replace web.xml, request dispatcher
AbstractAnnotationConfigDispatcherServletInitializer

springMVC bean config, replace springMVC.xml
WebMvcConfigurer


DispatcherServlet: 统一处理请求和响应
HandlerMapping: 根据请求url, method等信息查找Handler
Handler: 开发者实现，处理用户具体请求
HandlerAdapter: 
ViewResolver: 视图解析，ThymeleafView, InternalResourceView, RedirectView
View: 将模型数据通过页面展示给用户

