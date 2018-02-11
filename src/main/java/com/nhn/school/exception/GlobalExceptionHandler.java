package com.nhn.school.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DateValidException.class)
	public String dateError() {
		return "날짜 형식을 맞춰주세요 yyyyMMdd";
	}
	
	@ExceptionHandler(SubjectCapacity.class)
	public String subcapacityError() {
		return "용량을 초과했습니다. 데이터 100개가 용량입니다.";
	}
	
	@ExceptionHandler(PeopleCapacity.class)
	public String peoplecapacityError() {
		return "용량을 초과했습니다. 데이터 1000개가 용량입니다.";
	}
}
