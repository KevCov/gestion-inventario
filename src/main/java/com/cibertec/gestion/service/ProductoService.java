package com.cibertec.gestion.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.gestion.entity.Producto;
import com.cibertec.gestion.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository repository;

	public List<Producto> obtenerTodosProductos() {
		var productos = repository.findAll();
		if (productos.isEmpty())
			return Collections.emptyList();
		System.out.println(productos);
		return productos;
	}

	public Producto obtenerProductoById(Integer id) {
		var producto = repository.findById(id);
		if (producto.isEmpty())
			System.out.println("No se encontró el producto");

		return producto.get();
	}

	public void crearProducto(Producto producto) {
		repository.save(producto);
	}

	public void eliminarProducto(Integer id) {
		repository.deleteById(id);
	}

	public void actualizarProducto(Integer id, Producto producto) {
		var productoActual = repository.findById(id);

		productoActual.ifPresentOrElse(p -> {
			p.setNombreProducto(producto.getNombreProducto());
			p.setDescripcion(producto.getDescripcion());
			p.setPrecioCompra(producto.getPrecioCompra());
			p.setPrecioVenta(producto.getPrecioVenta());
			p.setStock(producto.getStock());
			repository.save(p);
		}, () -> {
			throw new NullPointerException("Producto no encontrado con ID: " + id);
		});
	}
}
