package com.school.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Type;
import com.school.models.request.GradeRequest;
import com.school.models.vo.Grade;
import com.school.models.vo.Student;
import com.school.models.vo.Subject;
import com.school.service.GradeService;
import com.school.service.StudentService;
import com.school.service.SubjectService;

import lombok.extern.java.Log;

@Log
public class GradeController extends HttpServlet {
	GradeService gradeService = GradeService.getInstance();
	StudentService studentService = StudentService.getInstance();
	SubjectService subjectService = SubjectService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		GradeRequest gradeRequest = GradeRequest.builder().name(req.getParameter("name"))
			.subject(req.getParameter("subject"))
			.grade(Integer.parseInt(req.getParameter("grade")))
			.changeName(req.getParameter("changeName"))
			.changeSubject(req.getParameter("changeSubject"))
			.changeGrade(Integer.parseInt(req.getParameter("changeGrade")))
			.build();
		String selectOption = req.getParameter("selectOption");

		List<String> list = Collections.emptyList();
		switch (req.getParameter("Crud")) {
			case Type.INSERT:
				gradeService.insert(gradeRequest);
				break;
			case Type.UPDATE:
				gradeService.update(gradeRequest);
				break;
			case Type.DELETE:
				gradeService.delete(gradeRequest);
				break;
			case Type.SLELCT:
				list = gradeService.selectOption(selectOption, gradeRequest);
				break;
		}
		if ("".equals(selectOption)) {
			list = gradeService.selectOption(Type.ALL_SELECT, gradeRequest);
		}

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}

	
}
