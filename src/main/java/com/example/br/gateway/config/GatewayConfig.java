package com.example.br.gateway.config;

import com.example.br.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    private String baseUrl = "http://localhost:";

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("event",
                        r -> r.path("/event/**")
                                .filters(f -> f.filter(filter))
                                .uri("lb://EVENTS-SERVICE")
                )
                .route("event",
                        r -> r.path("/auth/**")
                                .uri("lb://AUTH-SERVICE")
                )
                .build();
    }
}
