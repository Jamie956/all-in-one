package com.jamie.springcloud.config;

import com.jamie.springcloud.convert.AuthConverter;
import com.jamie.springcloud.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    private AuthConverter authConverter;

    @Autowired
    private AuthHandler authHandler;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private LogoutSuccesshandler logoutSuccesshandler;

    private static final String[] AUTH_WHITELIST = new String[]{"/login", "/logout", "/auth/**"};

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        SecurityWebFilterChain chain = http.formLogin().loginPage("/login")
                // 登录成功handler
                .authenticationSuccessHandler(loginSuccessHandler)
                // 登陆失败handler
                .authenticationFailureHandler(loginFailureHandler)
                // 无访问权限handler
                .authenticationEntryPoint(authHandler).and().logout()
                // 登出成功handler
                .logoutSuccessHandler(logoutSuccesshandler)
                //关闭csrf 防护
                .and().csrf().disable().httpBasic().disable()
                //认证配置
                .authorizeExchange()
                //白名单放行
                .pathMatchers(AUTH_WHITELIST).permitAll()
                //其他请求需要验证
//                .anyRequest().authenticated()
                //通过manager 验证
//                .anyExchange().access(authorizeConfigManager)
                .and().build();

        // 设置自定义登录参数转换器
        chain.getWebFilters().filter(webFilter -> webFilter instanceof AuthenticationWebFilter).subscribe(webFilter -> {
            AuthenticationWebFilter filter = (AuthenticationWebFilter) webFilter;
            filter.setServerAuthenticationConverter(authConverter);
        });

        return chain;
    }

    /**
     * BCrypt密码编码
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}