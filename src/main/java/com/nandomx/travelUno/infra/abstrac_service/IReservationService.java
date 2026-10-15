package com.nandomx.travelUno.infra.abstrac_service;

import com.nandomx.travelUno.api.models.requests.ReservationRequest;
import com.nandomx.travelUno.api.models.responses.ReservationResponse;

import java.util.UUID;

public interface IReservationService extends ICrudService<ReservationRequest, ReservationResponse, UUID>  {
}
