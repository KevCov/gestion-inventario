package com.cibertec.gestion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@GetMapping()
	public String mostrarLogin() {
		return "login/login";
	}
	
	@PostMapping()
	public String logearse() {
		return "redirect:producto";
	}
}
