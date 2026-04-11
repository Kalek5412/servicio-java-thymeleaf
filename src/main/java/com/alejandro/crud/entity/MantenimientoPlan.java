package com.alejandro.crud.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "mantenimiento_plan")
@Data
public class MantenimientoPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer intervaloMeses;
    private Boolean activo = true;
    private LocalDate fechaInicio;
    private LocalDate proximaEjecucion;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


}
