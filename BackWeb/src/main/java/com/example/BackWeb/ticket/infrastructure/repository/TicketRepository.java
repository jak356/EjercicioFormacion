package com.example.BackWeb.ticket.infrastructure.repository;


import com.example.BackWeb.ticket.domain.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
}
