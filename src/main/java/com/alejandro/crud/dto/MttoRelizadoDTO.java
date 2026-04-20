package com.alejandro.crud.dto;

import com.alejandro.crud.enums.TipoServicio;
import com.alejandro.crud.security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MttoRelizadoDTO {
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    private String detalle;
    @Enumerated(EnumType.STRING)
    private TipoServicio tipoServicio;
    private String tecnico;
}
