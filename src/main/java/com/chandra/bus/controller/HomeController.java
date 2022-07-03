package com.chandra.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("")
	public String landing() {
		return "redirect:/swagger-ui.html";
	}
}