package com.cibertec.gestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.gestion.entity.Usuario;
import com.cibertec.gestion.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping()
	public String listarClientes(Model model) {
		model.addAttribute("clientes", service.obtenerTodosClientes());
		return "usuario/listar-usuario";
	}
	
	@GetMapping("/crear")
	public String mostrarFormularioCrear(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/crear-usuario";
	}
	@PostMapping("/guardar")
	public String crearCliente(@ModelAttribute Usuario cliente) {
		service.crearCliente(cliente);
		return "redirect:/usuario";
	}
	
	@PostMapping("/actualizar/{id}")
	public String actualizarCliente(@PathVariable Integer id, @ModelAttribute Usuario cliente) {
		service.actualizarCliente(id, cliente);
		return "redirect:/usuario";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
		model.addAttribute("cliente", service.obtenerClienteById(id));
		return "usuario/editar-usuario";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarUsuario(@PathVariable Integer id) {
		service.eliminarCliente(id);
		return "redirect:/usuario";
	}
	
}
