package com.alejandro.crud.entity;

import com.alejandro.crud.enums.TicketEstado;
import com.alejandro.crud.security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private TicketEstado ticketEstado;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaEjecucion;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate proximaEjecucion;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioAsignado;

}
