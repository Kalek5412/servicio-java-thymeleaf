package com.alejandro.crud.entity;

import com.alejandro.crud.enums.ServicioNombre;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "servicios")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ServicioNombre servicioNombre;
    private String descripcion;
    @OneToMany(mappedBy = "servicio")
    private List<Ticket> tickets;


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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
