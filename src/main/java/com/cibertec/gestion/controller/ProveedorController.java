package com.cibertec.gestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.gestion.entity.Proveedor;
import com.cibertec.gestion.service.ProveedorService;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	private ProveedorService service;
	
	@GetMapping()
	public String listarProveedores(Model model) {
		model.addAttribute("proveedores", service.obtenerTodosProveedores());
		return "lista-proveedor";
	}
	
	@GetMapping("/crear")
	public String mostrarFormularioCrear(Model model) {
		model.addAttribute("proveedor", new Proveedor());
		return "crear-proveedor";
	}
	
	@PostMapping("/guardar")
	public String crearProveedor(@ModelAttribute Proveedor proveedor) {
		service.crearProveedor(proveedor);
		return "redirect:lista-proveedor";
	}
	
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
		model.addAttribute("proveedor", service.obtenerProveedorById(id));
		return "editar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarProveedor(@PathVariable Integer id) {
		service.eliminarProveedor(id);
		return "redirect:lista-proveedor";
	}
}
