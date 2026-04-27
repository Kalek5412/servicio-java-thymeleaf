package com.alejandro.crud.dto;

import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.entity.MantenimientoRealizado;
import com.alejandro.crud.entity.Producto;
import com.alejandro.crud.enums.EstadoMantenimiento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MttoPlanDTO {
    private Integer intervaloMeses;
    private Boolean activo = true;
    private LocalDate fechaCreacion;
    private LocalDate fechaProxima;
    private String observaciones;
    @Enumerated(EnumType.STRING)
    private EstadoMantenimiento estadoMantenimiento;
    private Long clienteId;
    private String clienteNombre;
    private Long productoId;
    private String productoNombre;

}
