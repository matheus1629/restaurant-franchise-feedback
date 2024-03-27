package com.restaurant.getewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GetewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator restaurantFeedbackRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/restaurant/feedbacks/**")
                        .filters(f -> f.rewritePath("restaurant/feedbacks/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("feedbacksCollectorCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://FEEDBACKSCOLLECTOR"))
                .route(p -> p
                        .path("/restaurant/analysis/**")
                        .filters(f -> f.rewritePath("restaurant/analysis/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("feedbacksAnalysisCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://FEEDBACKSANALYSIS"))
                .build();


    }

}
