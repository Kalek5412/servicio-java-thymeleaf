package com.alejandro.crud.service;

import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.entity.Servicio;
import com.alejandro.crud.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioService {

    @Autowired
    ServicioRepository servicioRepository;

    public List<Servicio> findAll(){
        return servicioRepository.findAll();
    }

    public Optional<Servicio> findById(int id){
        return servicioRepository.findById(id);
    }

    public void  save(Servicio servicio){
        servicioRepository.save(servicio);
    }

    public void delete(int id){
        servicioRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return servicioRepository.existsById(id);
    }
}
