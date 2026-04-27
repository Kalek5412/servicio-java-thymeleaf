package com.alejandro.crud.service;

import com.alejandro.crud.dto.MttoPlanDTO;
import com.alejandro.crud.dto.MttoRelizadoDTO;
import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.entity.MantenimientoPlan;
import com.alejandro.crud.entity.MantenimientoRealizado;
import com.alejandro.crud.entity.Producto;
import com.alejandro.crud.repository.ClienteRepository;
import com.alejandro.crud.repository.MantenimientoPlanRepository;
import com.alejandro.crud.repository.MantenimientoRealizadoRepository;
import com.alejandro.crud.repository.ProductoRepository;
import com.alejandro.crud.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MttoPlanService {

    @Autowired
    MantenimientoPlanRepository mantenimientoPlanRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public List<MttoPlanDTO> findAll(){
        return mantenimientoPlanRepository.findAll()
                .stream()
                .map(mtto -> {
                    MttoPlanDTO dto = new MttoPlanDTO();
                    dto.setActivo(mtto.getActivo());
                    dto.setFechaCreacion(mtto.getFechaCreacion());
                    dto.setFechaProxima(mtto.getFechaProxima());
                    dto.setObservaciones(mtto.getObservaciones());
                    dto.setEstadoMantenimiento(mtto.getEstadoMantenimiento());
                    dto.setClienteNombre(mtto.getCliente().getNombre());
                    dto.setProductoNombre(mtto.getProducto().getNombre());
                    return dto;
                })
                .toList();
    }


    public void save(MttoPlanDTO dto){
        MantenimientoPlan mtto = new MantenimientoPlan();
        mtto.setIntervaloMeses(dto.getIntervaloMeses());
        mtto.setActivo(true);
        mtto.setObservaciones(dto.getObservaciones());
        mtto.setEstadoMantenimiento(dto.getEstadoMantenimiento());
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no existe"));
        mtto.setCliente(cliente);
        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no existe"));
        mtto.setProducto(producto);
        mtto.setFechaProxima(
                LocalDate.now().plusMonths(dto.getIntervaloMeses())
        );
        mantenimientoPlanRepository.save(mtto);
    }

    public void delete(Long id){
        mantenimientoPlanRepository.deleteById(id);
    }
}
