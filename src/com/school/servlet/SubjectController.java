package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Subject;
import com.school.models.Type;
import com.school.models.request.SubjectRequest;
import com.school.service.SubjectService;

import lombok.extern.java.Log;

@Log
public class SubjectController extends HttpServlet {
	SubjectService subjectService = SubjectService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		SubjectRequest subjectRequest = SubjectRequest.builder()
			.name(req.getParameter("name"))
			.changeName(req.getParameter("changeName"))
			.build();

		switch (req.getParameter("Crud")) {
			case Type.INSERT:
				subjectService.insert(subjectRequest);
				break;
			case Type.UPDATE:
				subjectService.update(subjectRequest);
				break;
			case Type.DELETE:
				subjectService.delete(subjectRequest);
				break;
		}
		List<Subject> list = subjectService.select();

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
