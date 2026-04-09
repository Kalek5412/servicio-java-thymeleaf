package com.alejandro.crud.service;

import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.entity.Ticket;
import com.alejandro.crud.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(Long id){
        return ticketRepository.findById(id);
    }

    public void  save(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void delete(Long id){
        ticketRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return ticketRepository.existsById(id);
    }
}
