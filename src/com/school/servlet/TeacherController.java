package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Teacher;
import com.school.models.Type;
import com.school.models.request.TeacherRequest;
import com.school.service.TeacherService;

import lombok.extern.java.Log;

@Log
public class TeacherController extends HttpServlet {
	TeacherService teacherService = TeacherService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		TeacherRequest teacherRequest = TeacherRequest.builder().name(req.getParameter("name"))
			.birth(req.getParameter("birth"))
			.subject(req.getParameter("subject"))
			.changeName(req.getParameter("changeName"))
			.changeBirth(req.getParameter("changeBirth"))
			.changeSuject(req.getParameter("changeSubject"))
			.build();

		switch (req.getParameter("Crud")) {
			case Type.INSERT:
				teacherService.insert(teacherRequest);
				break;
			case Type.UPDATE:
				teacherService.update(teacherRequest);
				break;
			case Type.DELETE:
				teacherService.delete(teacherRequest);
				break;
		}

		List<Teacher> list = teacherService.select();

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
