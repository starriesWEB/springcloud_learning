package com.starry.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author starry
 * @version 1.0
 * @date 2022/1/24 16:49
 * @Description
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("id_news163_domestic", predicateSpec -> predicateSpec.path("/domestic").uri("https://news.163.com"))
                .route("id_news163_world", predicateSpec -> predicateSpec.path("/world").uri("https://news.163.com"))
                .build();
    }
}
