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

import com.nhn.school.model.Employee;
import com.nhn.school.model.Grade;
import com.nhn.school.service.GradeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/grades")
@Slf4j
public class GradeController {
	
	@Autowired
	private GradeService gradeService;
	
	@GetMapping("/{selectOption}")
	public String findAll(Model model, @PathVariable String selectOption) {
		log.info("{}", selectOption);
		model.addAttribute("menulist", gradeService.findAll(selectOption));
		return "result";
	}
	@PostMapping("")
	@ResponseBody
	public String save(Model model, @RequestBody Grade grade) {
		gradeService.save(grade);
		return "result";
	}
	@DeleteMapping("/{id}")
	@ResponseBody
	public String delete(Model model, @PathVariable int id) {
		gradeService.delete(id);
		return "result";
	}
	@PutMapping("")
	@ResponseBody
	public String update(Model model, @RequestBody Grade updateGrade) {
		gradeService.save(updateGrade);
		return "result";
	}
}
