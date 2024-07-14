package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("jamie")
                .password(getEncoder().encode("123"))
                .roles("admin");
    }

    /*
    Spring 读取表单的 username/password: UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY

    访问 http://localhost:8080/hi 跳转到登录页面 http://localhost:8080/login.html
    不需要登录 http://localhost:8080/test/hi
    登录提交表单路径 http://localhost:8080/user/login
    登录成功跳转 http://localhost:8080/test/index
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 自定义登录页面
        http.formLogin()
                // 设置登录页面
                .loginPage("/login.html")
                // 登录访问路径
                .loginProcessingUrl("/user/login")
                // 登录成功跳转路径
                .defaultSuccessUrl("/test/index")
                // ?
                .permitAll()
                .and().authorizeRequests()
                // 设置可以直接访问的路径，不需要认证
                .antMatchers("/","/test/hi","/user/login").permitAll()
                .anyRequest().authenticated()
                // 关闭 csrf 保护
                .and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}