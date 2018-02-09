package com.nhn.school.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhn.school.models.Type;
import com.nhn.school.models.vo.Grade;
import com.nhn.school.service.GradeService;

import lombok.extern.java.Log;

@Log
public class GradeController extends HttpServlet {
	GradeService gradeService = GradeService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Grade grade = Grade.of(req.getParameter("stu_Id"),
			req.getParameter("sub_Id"),
			req.getParameter("grade"));

		String selectOption = req.getParameter("selectOption");

		List<String> list = Collections.emptyList();
		switch (req.getParameter("Crud")) {
			case Type.INSERT:
				gradeService.addGrade(grade);
				break;
			case Type.UPDATE:
				gradeService.updateGrade(grade);
				break;
			case Type.DELETE:
				gradeService.deleteGrade(grade);
				break;
		}
		list = gradeService.selectOption(selectOption);

		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}

}
