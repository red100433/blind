package com.nhn.school.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhn.school.exception.DateValidException;
import com.nhn.school.exception.PeopleCapacity;
import com.nhn.school.exception.SubjectCapacity;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DateValidException.class)
	@ResponseBody
	public String dateError() {
		return "dateError";
	}
	
	@ExceptionHandler(SubjectCapacity.class)
	@ResponseBody
	public String subcapacityError() {
		return "subjectSizeError";
	}
	
	@ExceptionHandler(PeopleCapacity.class)
	@ResponseBody
	public String peoplecapacityError() {
		return "personSizeError";
	}
}
