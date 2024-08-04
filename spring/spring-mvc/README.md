# Setup tomcat web application with spring MVC
1. download tomcat 8.5.84
2. <packaging>war</packaging> in pom.xml
3. idea config: 
    - project structure -> modules -> add web
    - Web Resource Directory: path\to\webapp
    - Development Descriptor: path\to\web.xml
4. start application: run/debug configurations -> tomcat -> deployment -> + artifact -> project:war exploded -> application context: /

## server logs 中文乱码
1. apache-tomcat-8.5.84\conf\logging.properties
java.util.logging.ConsoleHandler.encoding = UTF-8
->
java.util.logging.ConsoleHandler.encoding = GBK
2. or vm options: -Dfile.encoding=UTF-8
