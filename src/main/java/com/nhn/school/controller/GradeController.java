package com.nhn.school.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nhn.school.model.Grade;
import com.nhn.school.model.Type;
import com.nhn.school.service.GradeService;

import lombok.extern.java.Log;

@Controller
public class GradeController extends HttpServlet {
	
	@Autowired
	private GradeService gradeService;

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
