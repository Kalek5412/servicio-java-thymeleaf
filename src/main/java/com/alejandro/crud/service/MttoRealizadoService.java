package com.alejandro.crud.service;

import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.entity.MantenimientoRealizado;
import com.alejandro.crud.repository.MantenimientoRealizadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MttoRealizadoService {

    @Autowired
    MantenimientoRealizadoRepository mantenimientoRealizadoRepository;

    public List<MantenimientoRealizado> findAll(){
        return mantenimientoRealizadoRepository.findAll();
    }

    public MantenimientoRealizado findById(Long id){
        return mantenimientoRealizadoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("mtto no encontrado"));
    }

    public MantenimientoRealizado save(MantenimientoRealizado mtto){
        return mantenimientoRealizadoRepository.save(mtto);
    }

    public MantenimientoRealizado update(Long id, MantenimientoRealizado data) {
        MantenimientoRealizado mtto = mantenimientoRealizadoRepository.findById(id).
                orElseThrow(()->new RuntimeException("mtto no encontrado"));
        mtto.setTipoServicio(data.getTipoServicio());
        mtto.setDetalle(data.getDetalle());
        return mantenimientoRealizadoRepository.save(mtto);
    }

    public void delete(Long id){
        mantenimientoRealizadoRepository.deleteById(id);
    }
}
