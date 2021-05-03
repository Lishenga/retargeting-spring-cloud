package com.retargeting_branding.apigateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGateWayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("MyHeader", "MyURI").addRequestParameter("Param", "value"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/affiliate/**").uri("lb://affiliate-service"))
                .route(p -> p.path("/authenticationandauthorization/**").uri("lb://authentication-service"))
                .route(p -> p.path("/retargetingandbranding/**").uri("lb://retargeting-branding-service")).route(
                        p -> p.path("/currency-conversion-new/**")
                                .filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)",
                                        "/currency-conversion-feign/${segment}"))
                                .uri("lb://currency-conversion"))
                .build();
    }
}
