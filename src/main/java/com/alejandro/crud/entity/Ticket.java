package com.alejandro.crud.entity;

import com.alejandro.crud.enums.ServicioNombre;
import com.alejandro.crud.enums.TicketNombre;
import com.alejandro.crud.security.entity.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TicketNombre ticketNombre;
    private String descripcion;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    Servicio servicio;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario usuarioAsignado;
}
