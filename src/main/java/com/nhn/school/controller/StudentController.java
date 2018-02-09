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

import com.nhn.school.model.Grade;
import com.nhn.school.model.Student;
import com.nhn.school.service.StudentService;


@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@GetMapping("")
	public String findAll(Model model) {
		model.addAttribute("menulist", studentService.findAll());
		return "result";
	}

	@PostMapping("")
	public String save(Model model, @RequestBody Student student) {
		studentService.save(student);
		model.addAttribute("menulist", studentService.findAll());
		return "result";
	}

	@DeleteMapping("/{id}")
	public String delete(Model model, @PathVariable int id) {
		studentService.delete(id);
		model.addAttribute("menulist", studentService.findAll());
		return "result";
	}

	@PutMapping("/{id}")
	public String modify(Model model, @RequestBody Student updateStudent, @PathVariable int id) {
		studentService.save(updateStudent, id);
		model.addAttribute("menulist", studentService.findAll());
		return "result";
	}
}
