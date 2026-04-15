package com.alejandro.crud.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inventarios")
@Data
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


}
