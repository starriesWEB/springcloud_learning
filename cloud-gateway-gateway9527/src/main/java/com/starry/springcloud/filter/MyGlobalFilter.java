package com.starry.springcloud.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author starry
 * @version 1.0
 * @date 2022/1/25 10:48
 * @Description
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("{} | {}",new Date(),"执行了自定义全局过滤MyGlobalFilter");
        // 模拟获取token
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (StrUtil.isBlank(token)) {
            // token为空，返回失败信息
            log.error("token为空，认证失败");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        // token有值，此过滤器通过，委托给下一个filter
        return chain.filter(exchange);
    }

    /**
     * 数值越低越先执行
     * 数值越高越后执行
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
