package com.nhn.school.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/html")
public class ExceptionController {
	
	@GetMapping("/dataInvaild")
	public String dateInvaild() {
		return "dataInvaild";
	}
	
	@GetMapping("/size")
	public String personSize() {
		return "capacityError";
	}
}
