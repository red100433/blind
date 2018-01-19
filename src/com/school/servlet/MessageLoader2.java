package com.school.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.school.business.FileSystem;
import com.school.models.Subject;

public class MessageLoader2 implements ServletContextListener {
	FileSystem fs = FileSystem.getInstance();

	/**
	 * messageFile의 내용을 읽어 들여 context에 message라는 이름으로 setAttribute한다.
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {

		List<Subject> list = new ArrayList<>();

		ServletContext context = event.getServletContext();

		String filename = context.getInitParameter("subFile");

		System.out.println("file path =" + context.getRealPath(filename));
		list = (List<Subject>)fs.readListObject(context.getRealPath(filename));
		context.setAttribute("message", list);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {}
}
