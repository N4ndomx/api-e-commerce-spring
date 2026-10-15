package com.nandomx.travelUno.infra.abstrac_service;

import com.nandomx.travelUno.api.models.responses.HotelResponse;

import java.util.List;

public interface IHotelService extends ICatalogoService<HotelResponse> {
    List<HotelResponse> findByRatingGreaterThan(Integer rating);
}
