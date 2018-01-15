package com.school.exception;

public class RegularExpression {

	public static void validateBirthday(String birthday) {
		String regNumber = "^[0-9]*$";
		if (birthday.length() != 6 & regNumber.matches(birthday)) {
			throw new InvalidException("생일은 6자리이야 합니다.");
		}
	}

	public static void validateStringIsNormal(String input) {
		String regex = "[a-zA-Z가-힣]+";
		if (input.matches(regex) == false) {
			throw new InvalidException("입력값은 영문자 혹은 한글만을 허용합니다.");
		}
	}

	public static void validateStringIsNotEmpty(String input) {
		if (input.isEmpty()) {
			throw new InvalidException("입력값은 공백일 수 없습니다.");
		}
	}

}
