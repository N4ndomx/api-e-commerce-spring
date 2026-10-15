package com.nandomx.travelUno.infra.services;

import com.nandomx.travelUno.api.models.requests.TourRequest;
import com.nandomx.travelUno.api.models.responses.TourResponse;
import com.nandomx.travelUno.infra.abstrac_service.ITourService;

import java.util.UUID;

public class TourService implements ITourService {

    @Override
    public TourResponse create(TourRequest request) {
        return null;
    }

    @Override
    public TourResponse read(Long request) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void removeTicket(UUID ticketId, Long tourId) {

    }

    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        return null;
    }

    @Override
    public void removeReservation(UUID reservationId, Long tourId) {

    }

    @Override
    public UUID addReservation(Long reservationId, Long tourId) {
        return null;
    }
}
