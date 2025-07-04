package com.cibertec.gestion.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AbastecimientoReporte {
	private Integer abastecimientoId;
    private Integer cantidad;
    private BigDecimal costoTotal;
    private LocalDate fecha;
    private String product_id;
    private String proveedor_id;
}
