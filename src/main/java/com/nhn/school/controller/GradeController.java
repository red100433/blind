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

import com.nhn.school.model.Employee;
import com.nhn.school.model.Grade;
import com.nhn.school.service.GradeService;

@Controller
@RequestMapping("/grades")
public class GradeController {
	
	@Autowired
	private GradeService gradeService;
	
	@GetMapping("")
	public String findAll(Model model) {
		model.addAttribute("menulist", gradeService.findAll());
		return "result";
	}
	@PostMapping("")
	public String save(Model model, @RequestBody Grade grade) {
		gradeService.save(grade);
		model.addAttribute("menulist", gradeService.findAll());
		return "result";
	}
	@DeleteMapping("/{id}")
	public String delete(Model model, @PathVariable int id) {
		gradeService.delete(id);
		model.addAttribute("menulist", gradeService.findAll());
		return "result";
	}
	@PutMapping("")
	public String update(Model model, @RequestBody Grade updateGrade) {
		gradeService.save(updateGrade);
		model.addAttribute("menulist", gradeService.findAll());
		return "result";
	}
}
