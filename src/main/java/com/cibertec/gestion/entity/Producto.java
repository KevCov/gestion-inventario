package com.cibertec.gestion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio_compra")
    private BigDecimal precioCompra;  

    @Column(name = "precio_venta")
    private BigDecimal precioVenta; 

    @Column(name = "stock")
    private Integer stock;
}
