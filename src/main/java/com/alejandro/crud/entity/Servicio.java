package com.alejandro.crud.entity;

import com.alejandro.crud.enums.ServicioNombre;
import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ServicioNombre servicioNombre;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServicioNombre getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(ServicioNombre servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
