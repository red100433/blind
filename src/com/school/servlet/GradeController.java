package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Grade;
import com.school.service.GradeService;

public class GradeController extends HttpServlet  {
	private static final String INSERT = "INSERT";
	private static final String UPDATE = "UPDATE";
	private static final String DELETE = "DELETE";
	GradeService gradeService = GradeService.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String management = req.getParameter("Manage");
		String crud = req.getParameter("Crud");
		
		String name = req.getParameter("name");
		String grade = req.getParameter("grade");
		String subject = req.getParameter("subject");
		String changeName = req.getParameter("changeName");
		String changeGrade = req.getParameter("changBirth");
		String changeSubject = req.getParameter("changeSubject");
		
		
		switch(crud) {
		case INSERT :
			gradeService.insert(name, subject, Integer.parseInt(grade));
			break;
		case UPDATE :
			gradeService.update(name, subject, changeName, changeSubject, Integer.parseInt(changeGrade));
			break;
		case DELETE :
			gradeService.delete(name, subject);
			break;
		}
		
		List<Grade> list = gradeService.select();
		
		System.out.println("ManageMent Data:" +management);
		System.out.println("Curd Data:" + crud);
		//
		//		list.forEach(System.out::println);
		//		fs.writeListObject(list, "C:\\Users\\NAVER\\Desktop\\java\\basicJava\\WebContent\\WEB-INF\\subObject.txt");
		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
