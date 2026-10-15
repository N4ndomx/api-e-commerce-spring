package com.nandomx.travelUno.infra.services;

import com.nandomx.travelUno.api.models.requests.TicketRequest;
import com.nandomx.travelUno.api.models.responses.FlyResponse;
import com.nandomx.travelUno.api.models.responses.TicketResponse;
import com.nandomx.travelUno.domain.entities.TicketEntity;
import com.nandomx.travelUno.domain.repositories.CustomerRepository;
import com.nandomx.travelUno.domain.repositories.FlyRepository;
import com.nandomx.travelUno.domain.repositories.TicketRepository;
import com.nandomx.travelUno.infra.abstrac_service.ITicketService;
import com.nandomx.travelUno.util.TravelOneUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Service
public class TicketService implements ITicketService {

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final FlyRepository flyRepository;

    @Override
    public TicketResponse create(TicketRequest request) {
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();
        var ticket = TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage)))
                .purchaseDate(LocalDate.now())
                .arrivalDate(TravelOneUtil.getDateDateLater())
                .departureDate(TravelOneUtil.getDateSoon()).build();
        var ticketPersisted = ticketRepository.save(ticket);

        log.info("Ticket persisted {}", ticketPersisted);
        return entityToResponse(ticketPersisted);
    }

    @Override
    public TicketResponse read(UUID request) {
        var ticket = ticketRepository.findById(request).orElseThrow();

        return entityToResponse(ticket);
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID uuid) {
        var ticket = ticketRepository.findById(uuid).orElseThrow();
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
        ticket.setFly(fly);
        ticket.setPrice(fly.getPrice().multiply(charger_price_percentage));
        ticket.setArrivalDate(TravelOneUtil.getDateDateLater());
        ticket.setDepartureDate(TravelOneUtil.getDateSoon());
        var ticketPersisted = ticketRepository.save(ticket); // Save verifica si hay id actualiza si no guarda
        log.info("Ticket persisted {}", ticketPersisted);
        return entityToResponse(ticketPersisted);
    }

    @Override
    public void delete(UUID uuid) {
        var ticket = ticketRepository.findById(uuid).orElseThrow(); // en caso de no existir el dato lanza error
        this.ticketRepository.delete(ticket);
    }

    private TicketResponse entityToResponse(TicketEntity entity) {
        TicketResponse response = new TicketResponse();
        BeanUtils.copyProperties(entity, response);
        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(entity.getFly(), flyResponse);
        response.setFly(flyResponse);
        return response;
    }

    @Override
    public BigDecimal findPrice(Long flyId) {
        var fly = flyRepository.findById(flyId).orElseThrow();
        return fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage));
    }

    private static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.25);
}
