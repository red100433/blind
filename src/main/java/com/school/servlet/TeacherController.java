package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Type;
import com.school.models.vo.Teacher;
import com.school.service.TeacherService;

import lombok.extern.java.Log;

@Log
public class TeacherController extends HttpServlet {
	TeacherService teacherService = TeacherService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Teacher teacher = Teacher.of(req.getParameter("id"),
			req.getParameter("name"), req.getParameter("birth"), req.getParameter("sub_Id"));

		switch (req.getParameter("Crud")) {
			case Type.INSERT:
				teacherService.addTeacher(teacher);
				break;
			case Type.UPDATE:
				teacherService.updateTeacher(teacher);
				break;
			case Type.DELETE:
				teacherService.deleteTeacher(teacher);
				break;
		}

		List<Teacher> list = teacherService.getAllTeachers();

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
