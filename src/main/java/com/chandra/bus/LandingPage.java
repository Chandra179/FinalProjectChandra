package com.chandra.bus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LandingPage {
	@RequestMapping("/")
	public String index() {
		return "redirect:swagger-ui.html";
	}

}