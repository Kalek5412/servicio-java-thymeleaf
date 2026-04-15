package com.alejandro.crud.entity;

import com.alejandro.crud.enums.TipoServicio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "servicios")
@Data
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "servicio_nombre")
    private TipoServicio servicioNombre;
    private String descripcion;

}
