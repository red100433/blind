package com.nhn.blind.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reactor.core.publisher.Mono;

/**
 * 
 * @author daeyun.jang
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserException.class)
	public Mono<String> userError() {
		return Mono.just("/error/500");
	}

	@ExceptionHandler(RuntimeException.class)
	public Mono<String> runtimeError() {
		return Mono.just("/error/500");
	}
}
