package com.nhn.school.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nhn.school.model.Subject;
import com.nhn.school.model.Type;
import com.nhn.school.service.SubjectService;

import lombok.extern.java.Log;

@Controller
public class SubjectController{
	
	@Autowired
	private SubjectService subjectService;

	@GetMapping
	public String getAllList(Model model) {
		model.addAttribute("menulist", subjectService.getAllSubjects());
		return "result";
	}
	
	
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		Subject subject = Subject.of(req.getParameter("id"), req.getParameter("name"));
//		switch (req.getParameter("Crud")) {
//			case Type.INSERT:
//				subjectService.addSubject(subject);
//				break;
//			case Type.UPDATE:
//				subjectService.updateSubject(subject);
//				break;
//			case Type.DELETE:
//				subjectService.deleteSubject(subject);
//				break;
//		}
//		List<Subject> list = subjectService.getAllSubjects();
//
//		req.setAttribute("menulist", list);
//
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
//		dispatcher.forward(req, res);
//	}
}
