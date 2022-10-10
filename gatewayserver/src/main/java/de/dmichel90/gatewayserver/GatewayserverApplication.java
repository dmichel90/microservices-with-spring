package de.dmichel90.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayserverApplication {

	public GatewayserverApplication(TokenRelayGatewayFilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	private final TokenRelayGatewayFilterFactory filterFactory;

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/dmichel90/restaurants/**")
						.filters(f -> f.filters(filterFactory.apply())
								.rewritePath("/dmichel90/restaurants/(?<segment>.*)","/${segment}")
								.removeRequestHeader("Cookie"))
						.uri("lb://RESTAURANTS")).build();
	}
}
