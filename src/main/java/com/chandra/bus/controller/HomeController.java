package com.chandra.bus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("")
	public String landing() {
		return "redirect:/swagger-ui.html";
	}
}