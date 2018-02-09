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

import com.nhn.school.model.Teacher;
import com.nhn.school.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	@GetMapping("")
	public String findAll(Model model) {
		model.addAttribute("menulist", teacherService.findAll());
		return "result";
	}
	@PostMapping("")
	public String save(Model model, @RequestBody Teacher teacher) {
		teacherService.save(teacher);
		model.addAttribute("menulist", teacherService.findAll());
		return "result";
	}
	@DeleteMapping("/{id}")
	public String delete(Model model, @PathVariable int id) {
		teacherService.delete(id);
		model.addAttribute("menulist", teacherService.findAll());
		return "result";
	}
	@PutMapping("")
	public String update(Model model, @RequestBody Teacher updateTeacher) {
		teacherService.save(updateTeacher);
		model.addAttribute("menulist", teacherService.findAll());
		return "result";
	}
}
