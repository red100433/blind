package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Employee;
import com.school.models.Type;
import com.school.models.request.EmployeeRequest;
import com.school.service.EmployeeService;

public class EmployeeController extends HttpServlet {
	EmployeeService employeeService = EmployeeService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		EmployeeRequest empRequest = EmployeeRequest.builder().name(req.getParameter("name"))
			.birth(req.getParameter("birth"))
			.changeName(req.getParameter("changeName"))
			.changeBirth(req.getParameter("changeBirth"))
			.build();
		switch (req.getParameter("Crud")) {
			case Type.INSERT:
				employeeService.insert(empRequest);
				break;
			case Type.UPDATE:
				employeeService.update(empRequest);
				break;
			case Type.DELETE:
				employeeService.delete(empRequest);
				break;
		}

		List<Employee> list = employeeService.select();

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
