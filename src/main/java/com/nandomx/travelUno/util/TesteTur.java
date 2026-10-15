package com.nandomx.travelUno.util;

import com.nandomx.travelUno.domain.entities.ReservationEntity;
import com.nandomx.travelUno.domain.entities.TicketEntity;
import com.nandomx.travelUno.domain.entities.TourEntity;
import com.nandomx.travelUno.domain.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j

public class TesteTur implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final FlyRepository flyRepository;
    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;
    private final TourRepository tourRepository;

    public TesteTur(CustomerRepository customerRepository, FlyRepository flyRepository, HotelRepository hotelRepository,
            ReservationRepository reservationRepository, TicketRepository ticketRepository,
            TourRepository tourRepository) {
        this.customerRepository = customerRepository;
        this.flyRepository = flyRepository;
        this.hotelRepository = hotelRepository;
        this.reservationRepository = reservationRepository;
        this.ticketRepository = ticketRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var customer = customerRepository.findById("GOTW771012HMRGR087").orElseThrow();
        var fly = flyRepository.findById(11L).orElseThrow();
        var hotel = hotelRepository.findById(3L).orElseThrow();

        log.info("Customer Name {}", customer.getFullName());
        log.info("Fly {}", fly);
        log.info("Hotel {}", hotel.getName());

        var tour = TourEntity.builder()
                .customer(customer).build();

        var ticket = TicketEntity.builder()
                .id(UUID.randomUUID())
                .price(fly.getPrice().multiply(BigDecimal.TEN))
                .purchaseDate(LocalDate.now())
                .arrivalDate(LocalDateTime.now())
                .departureDate(LocalDateTime.now())
                .customer(customer)
                .tour(tour)
                .fly(fly).build();

        var reservation = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .dateTimeReservation(LocalDateTime.now())
                .dateEnd(LocalDate.now())
                .dateStart(LocalDate.now())
                .hotel(hotel)
                .customer(customer)
                .tour(tour)
                .totalDays(1)
                .price(hotel.getPrice().multiply(BigDecimal.TEN))
                .build();

        tour.addTicket(ticket);
        tour.addReservation(reservation);
        System.out.println("SAVING");
        this.tourRepository.save(tour);
    }
}
