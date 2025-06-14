package com.cibertec.gestion.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.gestion.entity.Proveedor;
import com.cibertec.gestion.repository.ProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository repository;

	public List<Proveedor> obtenerTodosProveedores() {
		var proveedores = repository.findAll();
		if (proveedores.isEmpty())
			return Collections.emptyList();

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
			Proveedor.builder()
				.nombre(proveedor.getNombre())
				.dirección(proveedor.getDirección())
				.telefono(proveedor.getTelefono())
				.correoElectronico(proveedor.getCorreoElectronico())
				.build();
			repository.save(p);
		}, () -> new NullPointerException()

		);
	}
}
