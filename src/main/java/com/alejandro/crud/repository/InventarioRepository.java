package com.alejandro.crud.repository;

import com.alejandro.crud.entity.Inventario;
import com.alejandro.crud.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByProducto(Producto producto);
}
