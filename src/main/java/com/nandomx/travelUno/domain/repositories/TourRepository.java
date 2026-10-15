package com.nandomx.travelUno.domain.repositories;

import com.nandomx.travelUno.domain.entities.TourEntity;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<TourEntity, Long> {
}
