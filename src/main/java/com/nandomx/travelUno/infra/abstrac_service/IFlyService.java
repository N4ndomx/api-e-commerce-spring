package com.nandomx.travelUno.infra.abstrac_service;

import java.util.List;

import com.nandomx.travelUno.api.models.responses.FlyResponse;

public interface IFlyService extends ICatalogoService<FlyResponse> {

    List<FlyResponse> findByOriginDestiny(String origin, String destiny);

}
