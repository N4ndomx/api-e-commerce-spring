package com.nandomx.travelUno.domain.repositories;

import com.nandomx.travelUno.domain.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity,Long> {
    // Hay una nomeclatura que hay que seguir para que JPA pueda realizar por el mismo la JPQL - LenguajeIntuitivo
    List<HotelEntity> findByPriceLessThan(BigDecimal price);
    List<HotelEntity> findByPriceBetween(BigDecimal min, BigDecimal max);
    // Va a buscar un hotel que tenga un Rating mayor a
    List<HotelEntity> findByRatingGreaterThan(Integer rating);

    //    Ejemplo de Join
    List<HotelEntity> findByReservationsId(UUID reservationsId);

}
