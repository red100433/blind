package com.nhn.school.exception;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.nhn.school.model.Type;

public class Validator {
	public static void isDateValid(String date) {
		try {
			DateFormat df = new SimpleDateFormat(Type.DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
		} catch (ParseException e) {
			throw new DateValidException();
		}
	}
}
