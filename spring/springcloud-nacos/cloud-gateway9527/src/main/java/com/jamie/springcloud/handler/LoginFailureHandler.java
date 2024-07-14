package com.jamie.springcloud.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class LoginFailureHandler implements ServerAuthenticationFailureHandler {

    private static final String USER_NOT_EXISTS = "用户不存在！";
    private static final String USERNAME_PASSWORD_ERROR = "用户密码错误！";
    private static final String USER_LOCKED = "用户锁定！";

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        if (exception instanceof UsernameNotFoundException) {
            return result(response, USER_NOT_EXISTS);
        } else if (exception instanceof BadCredentialsException) {
            return result(response, USERNAME_PASSWORD_ERROR);
        } else if (exception instanceof LockedException) {
            return result(response, USER_LOCKED);
        }
        return result(response, exception.getMessage());
    }

    private Mono<Void> result(ServerHttpResponse response, String data) {
        JSONObject message = new JSONObject();
        message.put("status", -1);
        message.put("data", data);
        byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
