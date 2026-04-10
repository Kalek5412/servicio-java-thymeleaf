package com.alejandro.crud.entity;

import com.alejandro.crud.enums.TicketEstado;
import com.alejandro.crud.security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Integer intervaloMeses;
    private Boolean recurrente = false;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate proximaEjecucion;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaProgramada;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaRecordatorio;
    @Enumerated(EnumType.STRING)
    private TicketEstado ticketEstado;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIntervaloMeses() {
        return intervaloMeses;
    }

    public void setIntervaloMeses(Integer intervaloMeses) {
        this.intervaloMeses = intervaloMeses;
    }

    public Boolean getRecurrente() {
        return recurrente;
    }

    public void setRecurrente(Boolean recurrente) {
        this.recurrente = recurrente;
    }

    public LocalDate getProximaEjecucion() {
        return proximaEjecucion;
    }

    public void setProximaEjecucion(LocalDate proximaEjecucion) {
        this.proximaEjecucion = proximaEjecucion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDate fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public LocalDate getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public void setFechaRecordatorio(LocalDate fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }

    public TicketEstado getTicketEstado() {
        return ticketEstado;
    }

    public void setTicketEstado(TicketEstado ticketEstado) {
        this.ticketEstado = ticketEstado;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Usuario getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(Usuario usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }
}
