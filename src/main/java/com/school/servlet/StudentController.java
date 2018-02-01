package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Type;
import com.school.models.request.StudentRequest;
import com.school.models.vo.Student;
import com.school.service.StudentService;

public class StudentController extends HttpServlet {
	StudentService studentService = StudentService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		StudentRequest studentRequest = StudentRequest.builder().name(req.getParameter("name"))
			.birth(req.getParameter("birth"))
			.changeName(req.getParameter("changeName"))
			.changeBirth(req.getParameter("changeBirth"))
			.build();
		switch (req.getParameter("Crud")) {
			case Type.INSERT:
				studentService.addStudent(studentRequest);
				break;
			case Type.UPDATE:
				studentService.updateStudent(studentRequest);
				break;
			case Type.DELETE:
				studentService.deleteStudent(studentRequest);
				break;
		}

		List<Student> list = studentService.getAllSubjects();

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
