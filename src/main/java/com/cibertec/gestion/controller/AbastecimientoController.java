package com.cibertec.gestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.gestion.dto.AbastecimientoDto;
import com.cibertec.gestion.entity.Abastecimiento;
import com.cibertec.gestion.service.AbastecimientoService;
import com.cibertec.gestion.service.ProductoService;
import com.cibertec.gestion.service.ProveedorService;

@Controller
@RequestMapping("/abastecimiento")
public class AbastecimientoController {

	@Autowired
	private AbastecimientoService abastecimientoService;
	@Autowired
	private ProveedorService proveedorService;
	@Autowired
	private ProductoService productoService;

	@GetMapping()
	public String formAbastecimientos(Model model) {
		model.addAttribute("abastecimiento", new AbastecimientoDto());
		model.addAttribute("proveedores", proveedorService.obtenerTodosProveedores());
		model.addAttribute("productos", productoService.obtenerTodosProductos());

		return "abastecimiento/transaccion";
	}

	@PostMapping("/registrar")
	public String registrarAbastecimiento(@ModelAttribute AbastecimientoDto abastecimiento) {
		abastecimientoService.registrarAbastecimiento(abastecimiento);
		
		return "redirect:/abastecimiento/listar";
	}

	@GetMapping("/listar")
	public String listarAbastacimientos(Model model, @PageableDefault(size = 10, page = 0) Pageable pageable) {
		Page<Abastecimiento> pagAbastecimientos = abastecimientoService.obtenerAbastecimientosPaginacion(pageable);

		model.addAttribute("abastecimientos", pagAbastecimientos);
		model.addAttribute("currentPage", pagAbastecimientos.getNumber());
		model.addAttribute("totalPages", pagAbastecimientos.getTotalPages());
		model.addAttribute("totalItems", pagAbastecimientos.getTotalElements());
		return "abastecimiento/lista-ingresos";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarIngreso(@PathVariable Integer id) {
		abastecimientoService.eliminarAbastecimiento(id);
		
		return "redirect:/abastecimiento/listar";
	}
}
