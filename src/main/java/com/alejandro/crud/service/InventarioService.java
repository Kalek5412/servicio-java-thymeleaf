package com.alejandro.crud.service;

import com.alejandro.crud.dto.InventarioDTO;
import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.entity.Inventario;
import com.alejandro.crud.entity.Producto;
import com.alejandro.crud.repository.ClienteRepository;
import com.alejandro.crud.repository.InventarioRepository;
import com.alejandro.crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    InventarioRepository inventarioRepository;

    public List<InventarioDTO> listar() {
        return inventarioRepository.findAll()
                .stream()
                .map(inv -> {
                    InventarioDTO dto = new InventarioDTO();
                    dto.setId(inv.getId());
                    dto.setNombre(inv.getProducto().getNombre());
                    dto.setPrecio(inv.getProducto().getPrecio());
                    dto.setStock(inv.getStock());
                    return dto;
                })
                .toList();
    }

    public InventarioDTO findById(Long id){
        Inventario inv = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe"));
        InventarioDTO dto = new InventarioDTO();
        dto.setId(inv.getId());
        dto.setNombre(inv.getProducto().getNombre());
        dto.setPrecio(inv.getProducto().getPrecio());
        dto.setStock(inv.getStock());
        return dto;
    }

    public void  save(InventarioDTO dto){
        String nombreNormalizado = dto.getNombre().trim().toUpperCase();
        Producto producto = productoRepository.findByNombre(nombreNormalizado)
                .orElseGet(() -> {
                    Producto p = new Producto();
                    p.setNombre(nombreNormalizado);
                    return p;
                });
        producto.setPrecio(dto.getPrecio());
        productoRepository.save(producto);
        Optional<Inventario> invOpt = inventarioRepository.findByProducto(producto);
        if(invOpt.isPresent()){
            Inventario inv = invOpt.get();
            inv.setStock(inv.getStock() + dto.getStock());
            inventarioRepository.save(inv);
        } else {
            Inventario inv = new Inventario();
            inv.setProducto(producto);
            inv.setStock(dto.getStock());
            inventarioRepository.save(inv);
        }
    }

    public void delete(Long id){

            inventarioRepository.deleteById(id);

    }


    public List<Producto> buscarProductos(String nombre){
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
