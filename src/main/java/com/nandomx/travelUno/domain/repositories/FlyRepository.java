package com.nandomx.travelUno.domain.repositories;

import com.nandomx.travelUno.domain.entities.FlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlyRepository extends JpaRepository<FlyEntity,Long> {
    //Puedo hacer mis propias querty 3 tipos , Nativo de SQL , JPQL, Consultas de Hybernate

    @Query("select f from fly f where f.price < :price") // parametro
    List<FlyEntity> selectLessPrice(BigDecimal price);

    @Query("select f from fly f where f.price between :min  and :max") // parametro
    List<FlyEntity> selectBetweenPrice(BigDecimal min, BigDecimal max);

    @Query("select f from fly f where f.originName = :origin and f.destinyName = :destiny") // parametro
    List<FlyEntity> selectOriginDestiny(String origin, String destiny);

    @Query("select f from fly f join fetch  f.tickets t where t.id = :ticketId")
    Optional<FlyEntity> findByTicketId(UUID ticketId);






}
