package com.alejandro.crud.service;

import com.alejandro.crud.entity.Servicio;
import com.alejandro.crud.enums.TipoServicio;
import com.alejandro.crud.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioService {

    @Autowired
    ServicioRepository servicioRepository;

    public List<Servicio> findAll(){
        return servicioRepository.findAll();
    }

    public Servicio findById(Long id){
        return servicioRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Servicio no encontrado"));
    }

    public void  save(Servicio servicio){
        if(servicio.getServicioNombre() == null){
            throw new RuntimeException("El tipo de servicio es obligatorio");
        }
        servicioRepository.save(servicio);
    }
    public Servicio update(Long id, Servicio data) {
        Servicio s = servicioRepository.findById(id).
                orElseThrow(()->new RuntimeException("servicio no encontrado"));
        s.setServicioNombre(data.getServicioNombre());
        s.setDescripcion(data.getDescripcion());
        return servicioRepository.save(s);
    }

    public void delete(Long id){
        servicioRepository.deleteById(id);
    }

    public List<Servicio> search(String q) {
        return servicioRepository.findByServicioNombre(TipoServicio.valueOf(q));
    }
}
