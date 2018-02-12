package com.nhn.blind.config;

import java.util.List;

import org.springframework.http.ResponseCookie;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class LoginCheckInterceptor implements WebFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		 MultiValueMap<String, ResponseCookie> cookies = exchange.getResponse().getCookies();
		 List<ResponseCookie> list = cookies.get("userEmail");
		 list.forEach(s -> log.info(s.toString()));
		return null;
	}
//
//	 WebClient client = WebClient.builder()
//	            .filter((request, next) -> {
//	                ClientRequest filtered = ClientRequest.from(request)
//	                		.cookie(name, values)
//	                        .header("foo", "bar")
//	                        .build();
//	                return next.exchange(filtered);
//	            })
//	            .build();
//	 
	
	
}
