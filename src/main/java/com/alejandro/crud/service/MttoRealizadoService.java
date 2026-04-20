package com.alejandro.crud.service;

import com.alejandro.crud.dto.InventarioDTO;
import com.alejandro.crud.dto.MttoRelizadoDTO;
import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.entity.Inventario;
import com.alejandro.crud.entity.MantenimientoRealizado;
import com.alejandro.crud.repository.MantenimientoRealizadoRepository;
import com.alejandro.crud.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MttoRealizadoService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    MantenimientoRealizadoRepository mantenimientoRealizadoRepository;

    public List<MttoRelizadoDTO> findAll(){
        return mantenimientoRealizadoRepository.findAll()
                .stream()
                .map(mtto -> {
                    MttoRelizadoDTO dto = new MttoRelizadoDTO();
                    dto.setId(mtto.getId());
                    dto.setTipoServicio(mtto.getTipoServicio());
                    dto.setFecha(mtto.getFecha());
                    dto.setDetalle(mtto.getDetalle());
                    dto.setTecnico(mtto.getTecnico().getNombreUsuario());
                    return dto;
                })
                .toList();
    }

    public MttoRelizadoDTO findById(Long id){
        MantenimientoRealizado mtto = mantenimientoRealizadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe"));
        MttoRelizadoDTO dto = new MttoRelizadoDTO();
        dto.setId(mtto.getId());
        dto.setTipoServicio(mtto.getTipoServicio());
        dto.setFecha(mtto.getFecha());
        dto.setDetalle(mtto.getDetalle());
        dto.setTecnico(mtto.getTecnico().getNombreUsuario());
        return dto;
    }

    public void save(MttoRelizadoDTO dto){
        MantenimientoRealizado mtto = new MantenimientoRealizado();
        mtto.setTipoServicio(dto.getTipoServicio());
        mtto.setDetalle(dto.getDetalle());
        mtto.setFecha(dto.getFecha());
        if(dto.getTecnico() != null){
            var usuario = usuarioRepository
                    .findByNombreUsuario(dto.getTecnico())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            mtto.setTecnico(usuario);
        }
        mantenimientoRealizadoRepository.save(mtto);
    }

    public void delete(Long id){
        mantenimientoRealizadoRepository.deleteById(id);
    }
}
