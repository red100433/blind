package com.nhn.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhn.school.models.Type;
import com.nhn.school.models.vo.Subject;
import com.nhn.school.service.SubjectService;

import lombok.extern.java.Log;

@Log
public class SubjectController extends HttpServlet {
	SubjectService subjectService = SubjectService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Subject subject = Subject.of(req.getParameter("id"), req.getParameter("name"));
		switch (req.getParameter("Crud")) {
			case Type.INSERT:
				subjectService.addSubject(subject);
				break;
			case Type.UPDATE:
				subjectService.updateSubject(subject);
				break;
			case Type.DELETE:
				subjectService.deleteSubject(subject);
				break;
		}
		List<Subject> list = subjectService.getAllSubjects();

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
