package com.cibertec.gestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.gestion.dto.User;
import com.cibertec.gestion.service.UsuarioService;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping()
	public String mostrarLogin(Model model) {
		model.addAttribute("user", new User());
		return "login/login";
	}
	
	@PostMapping("/login")
	public String logearse(@ModelAttribute User user) {
		
		if (usuarioService.verificarUsuario(user)) {
			return "redirect:producto";
		} else {
			System.out.println("Entro");
			return "redirect:/";
		}
	}
}
