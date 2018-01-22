package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Student;
import com.school.models.Type;
import com.school.service.StudentService;

public class StudentController extends HttpServlet {
	StudentService studentService = StudentService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String management = req.getParameter("Manage");
		String crud = req.getParameter("Crud");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String changeName = req.getParameter("changeName");
		String changeBirth = req.getParameter("changBirth");

		switch (crud) {
			case Type.INSERT:
				studentService.insert(name, birth);
				break;
			case Type.UPDATE:
				studentService.update(name, birth, changeName, changeBirth);
				break;
			case Type.DELETE:
				studentService.delete(name, birth);
				break;
		}

		List<Student> list = studentService.select();

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
