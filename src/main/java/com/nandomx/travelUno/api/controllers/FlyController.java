package com.nandomx.travelUno.api.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nandomx.travelUno.api.models.responses.FlyResponse;
import com.nandomx.travelUno.infra.abstrac_service.IFlyService;
import com.nandomx.travelUno.util.SortType;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController()
@AllArgsConstructor
@RequestMapping(path = "fly")
public class FlyController {

    private final IFlyService flyService;

    @GetMapping()
    public ResponseEntity<Page<FlyResponse>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestHeader(required = false) SortType sortType) {

        if (Objects.isNull(sortType)) {
            sortType = SortType.NONE;
        }
        return ResponseEntity.ok(flyService.findAll(page, size, sortType));

    }

    @GetMapping("origin_destiny")
    public ResponseEntity<List<FlyResponse>> findByOriginDestiny(
            @RequestParam(name = "ori")
            String origin,
            @RequestParam(name = "des")
            String destiny
    ) {
        return ResponseEntity.ok(this.flyService.findByOriginDestiny(origin, destiny));
    }

    @GetMapping("lessPrice")
    public ResponseEntity<List<FlyResponse>> findLessPrice(
            @RequestParam
            BigDecimal price
    ){
        var result = this.flyService.findLessPrice(price);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @GetMapping("betwenPrice")
    public ResponseEntity<List<FlyResponse>> findBetwenPrice(
            @RequestParam
            BigDecimal priceMin,
            @RequestParam
            BigDecimal priceMax
    ){
        var result = this.flyService.findBetweenPrice(priceMin, priceMax);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }


}
