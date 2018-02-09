package com.nhn.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhn.school.models.Type;
import com.nhn.school.models.vo.Employee;
import com.nhn.school.service.EmployeeService;

public class EmployeeController extends HttpServlet {
	EmployeeService employeeService = EmployeeService.getInstance();

	@Override
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
