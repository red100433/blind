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
import com.school.service.TeacherService;

import lombok.extern.java.Log;

@Log
public class TeacherController extends HttpServlet {
	TeacherService teacherService = TeacherService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String management = req.getParameter("Manage");
		String crud = req.getParameter("Crud");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String subject = req.getParameter("subject");
		String changeName = req.getParameter("changeName");
		String changeBirth = req.getParameter("changeBirth");
		String changeSubject = req.getParameter("changeSubject");

		switch (crud) {
			case Type.INSERT:
				teacherService.insert(name, birth, subject);
				break;
			case Type.UPDATE:
				teacherService.update(name, birth, subject, changeName, changeBirth, changeSubject);
				break;
			case Type.DELETE:
				teacherService.delete(name, birth, subject);
				break;
		}

		List<Teacher> list = teacherService.select();

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
