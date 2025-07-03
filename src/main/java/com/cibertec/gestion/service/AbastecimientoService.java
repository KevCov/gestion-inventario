package com.cibertec.gestion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.gestion.dto.AbastecimientoDto;
import com.cibertec.gestion.entity.Abastecimiento;
import com.cibertec.gestion.repository.AbastecimientoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AbastecimientoService {

	@Autowired
	private AbastecimientoRepository repository;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private ProveedorService proveedorService;

	@Transactional
	public void registrarAbastecimiento(AbastecimientoDto abastecimiento) {
		try {
			productoService.aumentarStock(abastecimiento.getProductoId(), abastecimiento.getCantidad());
			log.info("Stock del producto {} aumentado en {}", abastecimiento.getProductoId(), abastecimiento.getCantidad());
		} catch (Exception e) {
			throw new RuntimeException("Error al aumentar el stock del producto: " + e.getMessage());
		}

		Abastecimiento abastecimientoEntity = Abastecimiento.builder()
				.producto(productoService.obtenerProductoById(abastecimiento.getProductoId()))
				.proveedor(proveedorService.obtenerProveedorById(abastecimiento.getProveedorId()))
				.cantidad(abastecimiento.getCantidad()).fecha(abastecimiento.getFechaAbastecimiento())
				.costoTotal(abastecimiento.getCostoTotal()).build();

		repository.save(abastecimientoEntity);
	}
	
	public Page<Abastecimiento> obtenerAbastecimientosPaginacion(Pageable pageable) {
		Page<Abastecimiento> abastecimientos = repository.findAll(pageable);
		
		if (abastecimientos.isEmpty()) {
			return Page.empty();
		}
		
		return abastecimientos;
	}
	
	@Transactional
	public void eliminarAbastecimiento(Integer id) {
		Optional<Abastecimiento> abastecimiento = repository.findById(id);
		
		if (abastecimiento.isEmpty()) {
			log.warn("No se encontró el abastecimiento con ID {}", id);
			throw new RuntimeException("Abastecimiento no encontrado");
		}

		try {
			productoService.disminuirStock(abastecimiento.get().getProducto().getProductId(), abastecimiento.get().getCantidad());
			log.info("Stock del producto {} disminuido en {}", abastecimiento.get().getProducto().getProductId(), abastecimiento.get().getCantidad());
		} catch (Exception e) {
			throw new RuntimeException("Error al disminuir el stock del producto: " + e.getMessage());
		}
		
		repository.deleteById(id);
		log.info("Abastecimiento con ID {} eliminado", id);
	}
}
