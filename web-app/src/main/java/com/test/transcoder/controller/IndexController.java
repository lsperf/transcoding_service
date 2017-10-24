package com.test.transcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ls on 11/8/16.
 */
@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "redirect:/upload";
	}


}
