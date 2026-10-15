package com.nandomx.travelUno.infra.abstrac_service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.nandomx.travelUno.util.SortType;

public interface ICatalogoService<R> {

    Page<R> findAll(Integer page, Integer size, SortType sortType);

    List<R> findLessPrice(BigDecimal price);

    List<R> findBetweenPrice(BigDecimal min, BigDecimal max);

    String FIELD_BY_SORT = "price";
}
