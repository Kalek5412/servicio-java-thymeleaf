package com.alejandro.crud.service;

import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Cliente no encontrado"));
    }

    public Cliente save(Cliente cliente){
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new RuntimeException("Nombre obligatorio");}
        cliente.setActivo(true);
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente data) {
        Cliente c = clienteRepository.findById(id).
                orElseThrow(()->new RuntimeException("cliente no encontrado"));
        c.setNombre(data.getNombre());
        c.setTelefono(data.getTelefono());
        c.setContacto(data.getContacto());
        c.setActivo(data.isActivo());
        return clienteRepository.save(c);
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

    public List<Cliente> search(String q) {
        return clienteRepository.findByNombreContainingIgnoreCase(q);
    }

}
