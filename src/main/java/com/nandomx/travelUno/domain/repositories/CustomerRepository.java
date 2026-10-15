package com.nandomx.travelUno.domain.repositories;

import com.nandomx.travelUno.domain.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {
}
