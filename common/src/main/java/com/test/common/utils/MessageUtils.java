package com.test.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageUtils {
	@Autowired
	private MessageSource messageSource;

	public String getMessage(String code, String locale) {

		return messageSource.getMessage(code, null, Locale.US);
	}

}
