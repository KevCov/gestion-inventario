package com.cibertec.gestion.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.gestion.entity.Producto;
import com.cibertec.gestion.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository repository;

	public Page<Producto> obtenerTodosProductosPaginado(Pageable pageable) {
		var productos = repository.findAll(pageable);
		if (productos.isEmpty())
			return Page.empty();

		return productos;
	}

	public List<Producto> obtenerTodosProductos() {
		var productos = repository.findAll();
		if (productos.isEmpty())
			return Collections.emptyList();

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
	
	public void disminuirStock(Integer id, Integer cantidad) {
		var producto = repository.findById(id);
		if (producto.isPresent()) {
			Producto p = producto.get();
			if (p.getStock() >= cantidad) {
				p.setStock(p.getStock() - cantidad);
				repository.save(p);
			} else {
				throw new IllegalArgumentException("Stock insuficiente para el producto con ID: " + id);
			}
		} else {
			throw new NullPointerException("Producto no encontrado con ID: " + id);
		}
	}

	public void aumentarStock(Integer id, Integer cantidad) {
		var producto = repository.findById(id);
		if (producto.isPresent()) {
			Producto p = producto.get();
			p.setStock(p.getStock() + cantidad);
			repository.save(p);
		} else {
			throw new NullPointerException("Producto no encontrado con ID: " + id);
		}
	}
}
