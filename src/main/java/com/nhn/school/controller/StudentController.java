package com.nhn.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nhn.school.service.StudentService;
@Controller
public class StudentController  {
	@Autowired
	private StudentService studentService;
	
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		Student student = Student.of(req.getParameter("id"),
//			req.getParameter("name"), req.getParameter("birth"));
//		switch (req.getParameter("Crud")) {
//			case Type.INSERT:
//				studentService.addStudent(student);
//				break;
//			case Type.UPDATE:
//				studentService.updateStudent(student);
//				break;
//			case Type.DELETE:
//				studentService.deleteStudent(student);
//				break;
//		}
//
//		List<Student> list = studentService.getAllSubjects();
//
//		req.setAttribute("menulist", list);
//
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
//		dispatcher.forward(req, res);
//	}
}
