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

    public Optional<Cliente> findById(Long id){
        return clienteRepository.findById(id);
    }

    public void  save(Cliente cliente){
        clienteRepository.save(cliente);
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return clienteRepository.existsById(id);
    }

}
