package com.gtn.util;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static boolean validateForXSS(String input) {

		input = input.toLowerCase();
		if (input.contains("<script") || input.contains("alert(") || input.contains("<img") || input.contains("style ")
				|| input.contains("script:") || input.contains("@import") || input.contains("javascript:")
				|| input.contains("</") || input.contains("src=") || input.contains("type=")
				|| input.contains("expression(") || input.contains("href=") || input.contains("url(")) {
			return false;
		} else {
			return true;
		}

	}

	private static boolean validateForSQLinject(String input) {

		input = input.toLowerCase();

		if (input.contains("select ") || input.contains("delete ") || input.contains("drop ")
				|| input.contains("insert ") || input.contains("truncate ") || input.contains("update ")
				|| input.contains("distinct ") || input.contains("union ") || input.contains("join ")
				|| input.contains("table ")) {
			return false;
		} else {
			return true;
		}

	}

	public static boolean ValidateMandatoryString(String input, int maxSize) {
		if (!isEmpty(input) && input.trim().length() <= maxSize) {
			if (validateForXSS(input) && validateForSQLinject(input)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ValidateString(String input, int maxSize) {
		if (!isEmpty(input)) {
			if (input.trim().length() <= maxSize && validateForXSS(input) && validateForSQLinject(input)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public static boolean ValidateMandatoryNumericString(String input, int maxSize) {
		if (!isEmpty(input) && input.trim().length() <= maxSize) {
			if (validateForXSS(input) && validateForSQLinject(input)) {
				Pattern p = Pattern.compile("[0-9]*");
				Matcher m = p.matcher(input);
				return m.matches();
			} else {

				return false;
			}
		} else {

			return false;
		}
	}

	public static boolean ValidateNumericString(String input, int maxSize) {
		if (!isEmpty(input)) {
			if (input.trim().length() <= maxSize && validateForXSS(input) && validateForSQLinject(input)) {
				Pattern p = Pattern.compile("[0-9]*");
				Matcher m = p.matcher(input);
				return m.matches();
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public static boolean isEmpty(Object object) {

		if (object == null)
			return true;

		if (object instanceof Collection)
			return ((Collection) object).isEmpty();

		if (object instanceof String)
			return (((String) object).trim().length() > 0) ? false : true;

		return false;
	}

	public static boolean validateEmail(String email, int maxSize) {
		System.out.println("Email : " + email);
		if (!isEmpty(email) && email.trim().length() <= maxSize) {
			System.out.println("Email not empty");
			Pattern p = Pattern.compile(EMAIL_PATTERN);
			Matcher m = p.matcher(email);
			return m.matches();
		} else
			return false;
	}
}
