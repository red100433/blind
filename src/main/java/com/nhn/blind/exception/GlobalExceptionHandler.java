package com.nhn.blind.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

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