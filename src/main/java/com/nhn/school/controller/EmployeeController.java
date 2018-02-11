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
import com.nhn.school.model.Employee;
import com.nhn.school.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/employees")
@Slf4j
public class EmployeeController{
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("")
	public String findAll(Model model) {
		model.addAttribute("menulist", employeeService.findAll());
		return "result";
	}
	@PostMapping("")
	@ResponseBody
	public String save(Model model, @RequestBody Employee employee) {
		Validator.isDateValid(employee.getBirth());
		employeeService.save(employee);
		return "result";
	}
	@DeleteMapping("/{id}")
	@ResponseBody
	public String delete(Model model, @PathVariable int id) {
		employeeService.delete(id);
		return "result";
	}
	@PutMapping("")
	@ResponseBody
	public String update(Model model, @RequestBody Employee updateEmployee) {
		Validator.isDateValid(updateEmployee.getBirth());
			
		employeeService.save(updateEmployee);
		return "result";
	}
}
