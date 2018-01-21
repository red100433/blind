package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Teacher;
import com.school.service.TeacherService;

import lombok.extern.java.Log;

@Log
public class TeacherController extends HttpServlet {
	private static final String INSERT = "INSERT";
	private static final String UPDATE = "UPDATE";
	private static final String DELETE = "DELETE";
	TeacherService teacherService = TeacherService.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String management = req.getParameter("Manage");
		String crud = req.getParameter("Crud");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String subject = req.getParameter("subject");
		String changeName = req.getParameter("changeName");
		String changeBirth = req.getParameter("changBirth");
		String changeSubject = req.getParameter("changeSubject");
		
		switch(crud) {
		case INSERT :
			teacherService.insert(name, birth, subject);
			break;
		case UPDATE :
			teacherService.update(name, birth, subject, changeName, changeBirth, changeSubject);
			break;
		case DELETE :
			teacherService.delete(name, birth, subject);
			break;
		}
		
		List<Teacher> list = teacherService.select();
		
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
