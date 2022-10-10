package de.dmichel90.gatewayserver.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class ResponseTraceFilter {

	private final FilterUtility filterUtility;

	public ResponseTraceFilter(FilterUtility filterUtility) {
		this.filterUtility = filterUtility;
	}

	@Bean
	public GlobalFilter postGlobalFilter() {
		return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
			HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
			String correlationId = filterUtility.getCorrelationId(requestHeaders);
			log.debug("Updated the correlation id to the outbound headers. {}", correlationId);
			exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
		}));
	}
}
