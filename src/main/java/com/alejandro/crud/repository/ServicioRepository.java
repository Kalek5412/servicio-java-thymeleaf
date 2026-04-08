package com.alejandro.crud.repository;

import com.alejandro.crud.entity.Producto;
import com.alejandro.crud.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
