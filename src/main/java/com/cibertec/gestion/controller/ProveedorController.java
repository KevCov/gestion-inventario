package com.cibertec.gestion.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	public String listarProveedores(Model model,@PageableDefault(size = 10, page = 0) Pageable pageable) {
		Page<Proveedor> paginaDeProveedores = service.obtenerTodosProveedoresPaginado(pageable);

        model.addAttribute("proveedores", paginaDeProveedores.getContent());
        model.addAttribute("currentPage", paginaDeProveedores.getNumber());
        model.addAttribute("totalPages", paginaDeProveedores.getTotalPages());
        model.addAttribute("totalItems", paginaDeProveedores.getTotalElements());
        
		return "proveedor/lista-proveedor";
	}
	
	@GetMapping("/crear")
	public String mostrarFormularioCrear(Model model) {
		model.addAttribute("proveedor", new Proveedor());
		return "proveedor/crear-proveedor";
	}
	
	@PostMapping("/guardar")
	public String crearProveedor(@ModelAttribute Proveedor proveedor) {
		service.crearProveedor(proveedor);
		return "redirect:/proveedor";
	}
	
	@PostMapping("/actualizar/{id}")
	public String actualizarProveedor(@PathVariable Integer id, @ModelAttribute Proveedor proveedor) {
		service.actualizarProveedor(id, proveedor);
		return "redirect:/proveedor";
	}
	
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
		model.addAttribute("proveedor", service.obtenerProveedorById(id));
		return "proveedor/editar-proveedor";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarProveedor(@PathVariable Integer id) {
		service.eliminarProveedor(id);
		return "redirect:/proveedor";
	}
}
