package com.alejandro.crud.service;

import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.entity.Inventario;
import com.alejandro.crud.entity.Producto;
import com.alejandro.crud.repository.ClienteRepository;
import com.alejandro.crud.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventarioService {
    @Autowired
    InventarioRepository inventarioRepository;

    public List<Inventario> findAll(){
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> findById(Long id){
        return inventarioRepository.findById(id);
    }

    public void  save(Inventario inventario){
        inventarioRepository.save(inventario);
    }

    public void delete(Long id){
        inventarioRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return inventarioRepository.existsById(id);
    }

    public Inventario findByProducto(Producto producto){
        return inventarioRepository.findByProducto(producto)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para el producto"));
    }
}
