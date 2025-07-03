package com.cibertec.gestion.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class AbastecimientoDto {
	private Integer productoId;
	private Integer proveedorId;
	private Integer cantidad;
	private LocalDate fechaAbastecimiento;
	private BigDecimal costoTotal;
}
