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

import com.nhn.school.model.Subject;
import com.nhn.school.service.SubjectService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/subjects")
@Slf4j
public class SubjectController{
	
	@Autowired
	private SubjectService subjectService;

	@GetMapping("")
	public String findAll(Model model) {
		model.addAttribute("menulist", subjectService.findAll());
		return "result";
	}
	@PostMapping("")
	public String save(Model model, @RequestBody Subject subject) {
		log.info("{}", subject.toString());
		subjectService.save(subject);
		model.addAttribute("menulist", subjectService.findAll());
		return "result";
	}
	@DeleteMapping("/{id}")
	public String delete(Model model, @PathVariable int id) {
		subjectService.delete(id);
		model.addAttribute("menulist", subjectService.findAll());
		return "result";
	}
	@PutMapping("")
	public String update(Model model, @RequestBody Subject updateSubject) {
		log.info("{}", updateSubject.toString());
		subjectService.save(updateSubject);
		model.addAttribute("menulist", subjectService.findAll());
		return "result";
	}

}
