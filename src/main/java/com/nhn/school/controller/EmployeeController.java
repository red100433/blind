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
import com.nhn.school.service.EmployeeService;


@Controller
@RequestMapping("/employees")
public class EmployeeController{
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("")
	public String findAll(Model model) {
		model.addAttribute("menulist", employeeService.findAll());
		return "result";
	}
	@PostMapping("")
	public String save(Model model, @RequestBody Employee employee) {
		employeeService.save(employee);
		model.addAttribute("menulist", employeeService.findAll());
		return "result";
	}
	@DeleteMapping("/{id}")
	public String delete(Model model, @PathVariable int id) {
		employeeService.delete(id);
		model.addAttribute("menulist", employeeService.findAll());
		return "result";
	}
	@PutMapping("/{id}")
	public String modify(Model model, @RequestBody Employee updateEmployee, @PathVariable int id) {
		employeeService.save(updateEmployee, id);
		model.addAttribute("menulist", employeeService.findAll());
		return "result";
	}
}
