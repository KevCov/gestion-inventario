package com.cibertec.gestion.entity;

import jakarta.persistence.Entity;
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
@Table(name = "proveedores")
public class Proveedor {
	private Integer id;
	private String nombre;
	private String dirección;
	private String telefono;
	private String correoElectronico;
}
