package com.alejandro.crud.entity;

import com.alejandro.crud.enums.EstadoMantenimiento;
import com.alejandro.crud.enums.TipoServicio;
import com.alejandro.crud.security.entity.Usuario;
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
    private LocalDate fechaCreacion;
    private LocalDate fechaUltimaEjecucion;
    private LocalDate fechaProxima;
    private String observaciones;
    @Enumerated(EnumType.STRING)
    private TipoServicio tipoServicio;
    @Enumerated(EnumType.STRING)
    private EstadoMantenimiento estado;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;


}
