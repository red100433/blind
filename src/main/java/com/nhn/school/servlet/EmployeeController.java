package com.nhn.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhn.school.model.Employee;
import com.nhn.school.model.Type;
import com.nhn.school.service.EmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController{
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("")
	public String getAllList(Model model) {
		
		model.addAttribute("menulist",employeeService.getAllEmployees());
		
		return "result";
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Employee emp = Employee.of(req.getParameter("id"),
			req.getParameter("name"), req.getParameter("birth"));
		switch (req.getParameter("Crud")) {
			case Type.INSERT:

				employeeService.addEmployee(emp);
				break;
			case Type.UPDATE:

				employeeService.updateEmployee(emp);
				break;
			case Type.DELETE:
				employeeService.deleteEmployee(emp);
				break;
		}

		List<Employee> list = employeeService.getAllEmployees();

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
