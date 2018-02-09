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
	@ResponseBody
	public String save(Model model, @RequestBody Teacher teacher) {
		teacherService.save(teacher);
		return "result";
	}
	@DeleteMapping("/{id}")
	@ResponseBody
	public String delete(Model model, @PathVariable int id) {
		teacherService.delete(id);
		return "result";
	}
	@PutMapping("")
	@ResponseBody
	public String update(Model model, @RequestBody Teacher updateTeacher) {
		teacherService.save(updateTeacher);
		return "result";
	}
}
