package com.cibertec.gestion.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "abastecimiento")
public class Abastecimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "abastecimiento_id")
	private Integer abastecimientoId;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column(name = "costo_total", nullable = false)
	private BigDecimal costoTotal;
	
	@Column(name = "fecha")
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "proveedor_id", nullable = false)
	private Proveedor proveedor;
}
