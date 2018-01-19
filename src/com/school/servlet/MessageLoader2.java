package com.school.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.school.models.Subject;

public class MessageLoader2 implements ServletContextListener {

	/**
	 * messageFile의 내용을 읽어 들여 context에 message라는 이름으로 setAttribute한다.
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {

		List<Subject> list = new ArrayList<>();

		ServletContext context = event.getServletContext();

		String filename = context.getInitParameter("subFile");

		System.out.println("fileName");
		try (FileInputStream f = (FileInputStream)context.getResourceAsStream(filename);
			ObjectInputStream oi = new ObjectInputStream(new BufferedInputStream(f))) {

			try {
				list = (ArrayList)oi.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {}
}
