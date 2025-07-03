package com.cibertec.gestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.gestion.entity.Producto;
import com.cibertec.gestion.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService service;

	@GetMapping()
	public String listarProductos(Model model,@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<Producto> paginaDeProductos = service.obtenerTodosProductosPaginado(pageable);

        model.addAttribute("productos", paginaDeProductos.getContent());
        model.addAttribute("currentPage", paginaDeProductos.getNumber());
        model.addAttribute("totalPages", paginaDeProductos.getTotalPages());
        model.addAttribute("totalItems", paginaDeProductos.getTotalElements());
        
	    return "producto/lista-producto";
	}

	@GetMapping("/crear")
	public String mostrarFormularioCrear(Model model) {
		model.addAttribute("producto", new Producto());
		return "producto/crear-producto";
	}

	@PostMapping("/guardar")
	public String crearProducto(@ModelAttribute Producto producto) {
		service.crearProducto(producto);
		return "redirect:/producto";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizarProducto(@PathVariable Integer id, @ModelAttribute Producto producto) {
		service.actualizarProducto(id, producto);
		return "redirect:/producto";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
		model.addAttribute("producto", service.obtenerProductoById(id));
		return "producto/editar-producto";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable Integer id) {
		service.eliminarProducto(id);
		return "redirect:/producto";
	}
}
