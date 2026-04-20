package com.alejandro.crud.entity;

import com.alejandro.crud.enums.TipoServicio;
import com.alejandro.crud.security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "mantenimiento_realizado")
@Data
public class MantenimientoRealizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    private String detalle;
    @Enumerated(EnumType.STRING)
    private TipoServicio tipoServicio;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;
    @ManyToOne
    @JoinColumn(name = "mantenimiento_plan_id")
    private MantenimientoPlan mantenimientoPlan;


    @PrePersist
    public void prePersist() {
        this.fecha = LocalDate.now();
    }
}
