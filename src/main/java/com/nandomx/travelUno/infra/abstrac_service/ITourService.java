package com.nandomx.travelUno.infra.abstrac_service;

import com.nandomx.travelUno.api.models.requests.TourRequest;
import com.nandomx.travelUno.api.models.responses.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCrudService<TourRequest, TourResponse,Long> {

    void removeTicket(UUID ticketId,Long tourId);
    UUID addTicket(Long flyId,Long tourId);
    void removeReservation(UUID reservationId,Long tourId);
    UUID addReservation(Long reservationId,Long tourId);
}
