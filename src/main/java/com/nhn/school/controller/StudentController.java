package com.nhn.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhn.school.exception.Validator;
import com.nhn.school.model.Grade;
import com.nhn.school.model.Student;
import com.nhn.school.service.StudentService;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/students")
@Slf4j
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@GetMapping("")
	public String findAll(Model model) {
		model.addAttribute("menulist", studentService.findAll());
		return "result";
	}

	@PostMapping("")
	@ResponseBody
	public String save(Model model, @RequestBody Student student) {
		Validator.isDateValid(student.getBirth());
		studentService.save(student);
		return "result";
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public String delete(Model model, @PathVariable int id) {
		studentService.delete(id);
		return "result";
	}

	@PutMapping("")
	@ResponseBody
	public String update(Model model, @RequestBody Student updateStudent) {
		Validator.isDateValid(updateStudent.getBirth());
		studentService.save(updateStudent);
		return "result";
	}
}
