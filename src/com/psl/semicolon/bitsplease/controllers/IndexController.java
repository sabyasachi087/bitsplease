package com.psl.semicolon.bitsplease.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("indexController")
public class IndexController {

	@RequestMapping(path = { "/" }, method = RequestMethod.GET)
	public String index(Model model) {
		return "index.html";
	}

}
