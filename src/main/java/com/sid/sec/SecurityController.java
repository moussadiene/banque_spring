package com.sid.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/")
	private String home() {
		return "redirect:/comptes";

	}
	@RequestMapping(value = "/403")
	private String access() {
		return "403";

	}
}
