package com.alejandro.crud.repository;

import com.alejandro.crud.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
   // List<Ticket> findByFechaCreacionBetween(Date inicio, Date fin);
}
