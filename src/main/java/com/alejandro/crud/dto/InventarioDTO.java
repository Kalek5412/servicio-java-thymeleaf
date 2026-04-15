package com.alejandro.crud.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventarioDTO {

    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;

}
