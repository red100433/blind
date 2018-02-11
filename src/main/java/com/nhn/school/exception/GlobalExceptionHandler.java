package com.nhn.school.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DateValidException.class)
	public String dateError() {
		return "날짜 형식을 맞춰주세요 yyyyMMdd";
	}
}
