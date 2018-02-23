package com.nhn.blind.config;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

/**
 * Login Filter 설정
 * @author daeyun.jang
 *
 */
@Component
public class LoginCheckInterceptor implements WebFilter {
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

		if (exchange.getRequest().getURI().getPath().equals("/signup")) {
			return chain
				.filter(exchange.mutate().request(exchange.getRequest().mutate().path("/signup").build()).build());
		}
		if (exchange.getRequest().getCookies().isEmpty()) {
			return chain
				.filter(exchange.mutate().request(exchange.getRequest().mutate().path("/login").build()).build());
		}

		if (exchange.getRequest().getCookies().get("userId").get(0).getValue().equals("")) {
			return chain
				.filter(exchange.mutate().request(exchange.getRequest().mutate().path("/login").build()).build());
		}
		return chain.filter(exchange);
	}
}