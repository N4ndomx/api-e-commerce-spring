package com.nandomx.travelUno.domain.repositories;

import com.nandomx.travelUno.domain.entities.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<TicketEntity, UUID> {
}
