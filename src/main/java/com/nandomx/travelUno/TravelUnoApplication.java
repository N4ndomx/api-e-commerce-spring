package com.nandomx.travelUno;

import com.nandomx.travelUno.domain.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelUnoApplication {

    private final CustomerRepository customerRepository;
    private final FlyRepository flyRepository;
    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;
    private final TourRepository tourRepository;

    public TravelUnoApplication(CustomerRepository customerRepository, FlyRepository flyRepository,
            HotelRepository hotelRepository, ReservationRepository reservationRepository,
            TicketRepository ticketRepository, TourRepository tourRepository) {
        this.customerRepository = customerRepository;
        this.flyRepository = flyRepository;
        this.hotelRepository = hotelRepository;
        this.reservationRepository = reservationRepository;
        this.ticketRepository = ticketRepository;
        this.tourRepository = tourRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TravelUnoApplication.class, args);
    }

    // @Override
    // public void run(String... args) throws Exception {
    //// var fly = flyRepository.findById(15L).get();
    //// var hotel = hotelRepository.findById(7L).get();
    //// var customer = customerRepository.findById("BBMB771012HMCRR022").get();
    //// var reservation =
    // reservationRepository.findById(UUID.fromString("12345678-1234-5678-1234-567812345678")).get();
    //// var ticket =
    // ticketRepository.findById(UUID.fromString("12345678-1234-5678-2236-567812345678")).get();
    ////
    //// log.info(fly.toString());
    //// log.info(hotel.toString());
    //// log.info(customer.toString());
    //// log.info(reservation.toString());
    //// log.info(ticket.toString());
    //
    // this.flyRepository.selectBetweenPrice(BigDecimal.valueOf(18),BigDecimal.valueOf(20)).forEach(System.out::println);//
    // exprecion landa reducida
    // this.flyRepository.selectOriginDestiny("Grecia","Mexico").forEach(System.out::println);
    //
    //// FlyEntity fly = this.flyRepository.findById(1L).get(); // Realizo una
    // sesion y se cerro depues de concluir
    //// fly.getTickets().forEach(System.out::println);
    //
    // var fly =
    // this.flyRepository.findByTicketId(UUID.fromString("22345678-1234-5678-3235-567812345678")).get();
    // System.out.println("-->"+fly.toString());
    //
    // var hotels =
    // this.hotelRepository.findByPriceLessThan(BigDecimal.valueOf(100));
    // hotels.forEach(System.out::println);
    //
    // this.hotelRepository.findByPriceBetween(BigDecimal.valueOf(70),
    // BigDecimal.valueOf(100)).forEach(System.out::println);
    //
    // this.hotelRepository.findByRatingGreaterThan(3).forEach(x ->
    // System.out.println(x));
    // this.hotelRepository.findByReservationsId(UUID.fromString("12345678-1234-5678-1234-567812345678")).forEach(System.out::println);
    // }
}
