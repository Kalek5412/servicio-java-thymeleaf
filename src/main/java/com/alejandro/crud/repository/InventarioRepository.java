package com.alejandro.crud.repository;

import com.alejandro.crud.entity.Inventario;
import com.alejandro.crud.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
