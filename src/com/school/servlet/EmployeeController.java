package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Employee;
import com.school.service.EmployeeService;

public class EmployeeController {
	private static final String INSERT = "INSERT";
	private static final String UPDATE = "UPDATE";
	private static final String DELETE = "DELETE";
	
	EmployeeService employeeService = EmployeeService.getInstance();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String management = req.getParameter("Manage");
		String crud = req.getParameter("Crud");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String changeName = req.getParameter("changeName");
		String changeBirth = req.getParameter("changBirth");
		
		
		switch(crud) {
		case INSERT :
			employeeService.insert(name, birth);
			break;
		case UPDATE :
			employeeService.update(name, birth, changeName, changeBirth);
			break;
		case DELETE :
			employeeService.delete(name, birth);
			break;
		}
		
		List<Employee> list = employeeService.select();
		
		System.out.println("ManageMent Data:" +management);
		System.out.println("Curd Data:" + crud);
		//
		//		list.forEach(System.out::println);
		//		fs.writeListObject(list, "C:\\Users\\NAVER\\Desktop\\java\\basicJava\\WebContent\\WEB-INF\\subObject.txt");
		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
