package com.test.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {

	private static Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean isValidEmail(String email) {

		Matcher m = emailPattern.matcher(email); return !m.matches();

	}
}
