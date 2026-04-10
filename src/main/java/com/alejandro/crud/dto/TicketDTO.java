package com.alejandro.crud.dto;

import com.alejandro.crud.enums.TicketEstado;

import java.time.LocalDate;


public class TicketDTO {
    private Integer clienteId;
    private Integer servicioId;
    private Integer productoId;
    private Integer usuarioId;

    private Integer cantidad;
    private String descripcion;
    private TicketEstado ticketEstado;

    private LocalDate fechaProgramada;
    private Integer mesesRecordatorio;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getServicioId() {
        return servicioId;
    }

    public void setServicioId(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TicketEstado getTicketEstado() {
        return ticketEstado;
    }

    public void setTicketEstado(TicketEstado ticketEstado) {
        this.ticketEstado = ticketEstado;
    }

    public Integer getMesesRecordatorio() {
        return mesesRecordatorio;
    }

    public void setMesesRecordatorio(Integer mesesRecordatorio) {
        this.mesesRecordatorio = mesesRecordatorio;
    }

    public LocalDate getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDate fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }
}
