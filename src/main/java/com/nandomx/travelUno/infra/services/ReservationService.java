package com.nandomx.travelUno.infra.services;

import com.nandomx.travelUno.api.models.requests.ReservationRequest;
import com.nandomx.travelUno.api.models.responses.HotelResponse;
import com.nandomx.travelUno.api.models.responses.ReservationResponse;
import com.nandomx.travelUno.domain.entities.ReservationEntity;
import com.nandomx.travelUno.domain.repositories.CustomerRepository;
import com.nandomx.travelUno.domain.repositories.HotelRepository;
import com.nandomx.travelUno.domain.repositories.ReservationRepository;
import com.nandomx.travelUno.infra.abstrac_service.IReservationService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ReservationService implements IReservationService {
    private HotelRepository hotelRepository;
    private CustomerRepository customerRepository;
    private ReservationRepository reservationRepository;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        var hotel = hotelRepository.findById(request.getHotelId()).orElseThrow();
        var customer = customerRepository.findById(request.getCustomerId()).orElseThrow();

        var reservationEntity = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .price(hotel.getPrice().add(hotel.getPrice().multiply(BigDecimal.valueOf(100))))
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(request.getDateStart())
                .dateEnd(request.getDateStart().plusDays(request.getTotalDays())).build();
        var reservation = reservationRepository.save(reservationEntity);
        return entityToResponse(reservation);
    }

    @Override
    public ReservationResponse read(UUID request) {
        return entityToResponse(reservationRepository.findById(request).orElseThrow());
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID uuid) {
        var reservation = reservationRepository.findById(uuid).orElseThrow();
        var hotel = hotelRepository.findById(request.getHotelId()).orElseThrow();

        reservation.setHotel(hotel);
        reservation.setTotalDays(request.getTotalDays());
        reservation.setDateStart(request.getDateStart());
        reservation.setDateEnd(request.getDateStart().plusDays(request.getTotalDays()));

        var updatedReservation = reservationRepository.save(reservation);
        return entityToResponse(updatedReservation);
    }

    @Override
    public void delete(UUID uuid) {
        log.warn("Deleting reservation with id: {}", uuid);
        var reservation = reservationRepository.findById(uuid).orElseThrow();

        reservationRepository.delete(reservation);

    }

    private ReservationResponse entityToResponse(ReservationEntity entity) {
        ReservationResponse response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }

    private static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.20);
}
