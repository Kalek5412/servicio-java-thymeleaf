package com.alejandro.crud.service;

import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.entity.Inventario;
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

    public Optional<Inventario> findById(int id){
        return inventarioRepository.findById(id);
    }

    public void  save(Inventario inventario){
        inventarioRepository.save(inventario);
    }

    public void delete(int id){
        inventarioRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return inventarioRepository.existsById(id);
    }
}
