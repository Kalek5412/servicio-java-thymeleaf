package com.alejandro.crud.entity;

import com.alejandro.crud.enums.EstadoMantenimiento;
import com.alejandro.crud.enums.TipoServicio;
import com.alejandro.crud.security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "mantenimiento_plan")
@Data
public class MantenimientoPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer intervaloMeses;
    private Boolean activo = true;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaProxima;
    private String observaciones;
    @Enumerated(EnumType.STRING)
    private EstadoMantenimiento estadoMantenimiento;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Producto producto;
    @OneToMany(mappedBy = "mantenimientoPlan", cascade = CascadeType.ALL)
    private List<MantenimientoRealizado> mantenimientoRealizados;
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDate.now();
    }
}
