package com.cibertec.gestion.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.gestion.entity.Proveedor;
import com.cibertec.gestion.repository.ProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository repository;

	public Page<Proveedor> obtenerTodosProveedores(Pageable pageable) {
		var proveedores = repository.findAll(pageable);
		if (proveedores.isEmpty())
			return Page.empty();

		return proveedores;
	}

	public Proveedor obtenerProveedorById(Integer id) {
		var proveedor = repository.findById(id);
		if (proveedor.isEmpty())
			System.out.println("No se encontro al proveedor");

		return proveedor.get();
	}

	public void crearProveedor(Proveedor proveedor) {
		repository.save(proveedor);
	}

	public void eliminarProveedor(Integer id) {
		repository.deleteById(id);
	}

	public void actualizarProveedor(Integer id, Proveedor proveedor) {
		var proveedorActual = repository.findById(id);

		proveedorActual.ifPresentOrElse(p -> {
			p.setNombre(proveedor.getNombre());
			p.setDireccion(proveedor.getDireccion());
			p.setTelefono(proveedor.getTelefono());
			p.setCorreoElectronico(proveedor.getCorreoElectronico());
			repository.save(p);
		}, () -> {
			throw new NullPointerException("Proveedor no encontrado con ID: " + id);
		});
	}
}
