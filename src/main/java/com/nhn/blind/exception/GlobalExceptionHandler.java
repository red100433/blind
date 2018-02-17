package com.nhn.blind.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

	//TODO Update User Error 는 page로감. Delete 는 callback으로 데이터를 받음
	//TODO 해결방법은?
	@ExceptionHandler(UserException.class)
	public Mono<String> userError() {
		return Mono.just("/error/500");
	}
	
}