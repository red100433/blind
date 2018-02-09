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

import com.nhn.school.model.Student;
import com.nhn.school.model.Type;
import com.nhn.school.service.StudentService;
@Controller
public class StudentController extends HttpServlet {
	@Autowired
	private StudentService studentService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Student student = Student.of(req.getParameter("id"),
			req.getParameter("name"), req.getParameter("birth"));
		switch (req.getParameter("Crud")) {
			case Type.INSERT:
				studentService.addStudent(student);
				break;
			case Type.UPDATE:
				studentService.updateStudent(student);
				break;
			case Type.DELETE:
				studentService.deleteStudent(student);
				break;
		}

		List<Student> list = studentService.getAllSubjects();

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
