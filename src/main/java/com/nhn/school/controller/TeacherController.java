package com.nhn.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nhn.school.service.TeacherService;

@Controller
public class TeacherController {
	@Autowired
	private TeacherService teacherService;


//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		Teacher teacher = Teacher.of(req.getParameter("id"),
//			req.getParameter("name"), req.getParameter("birth"), req.getParameter("sub_Id"));
//
//		switch (req.getParameter("Crud")) {
//			case Type.INSERT:
//				teacherService.addTeacher(teacher);
//				break;
//			case Type.UPDATE:
//				teacherService.updateTeacher(teacher);
//				break;
//			case Type.DELETE:
//				teacherService.deleteTeacher(teacher);
//				break;
//		}
//
//		List<Teacher> list = teacherService.getAllTeachers();
//
//		req.setAttribute("menulist", list);
//
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
//		dispatcher.forward(req, res);
//	}
}
