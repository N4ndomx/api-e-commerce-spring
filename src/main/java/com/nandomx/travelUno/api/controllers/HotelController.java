package com.nandomx.travelUno.api.controllers;

import com.nandomx.travelUno.api.models.responses.HotelResponse;
import com.nandomx.travelUno.infra.abstrac_service.IHotelService;
import com.nandomx.travelUno.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/hotel")
@AllArgsConstructor
public class HotelController  {

    private IHotelService hotelService;

    @GetMapping("")
    public ResponseEntity<Page<HotelResponse>> getAllHotels(
            @RequestParam()
            Integer page,
            @RequestParam()
            Integer size,
            @RequestHeader(required = false)
            SortType sortType
    ) {

        if (Objects.isNull(sortType)) {
            sortType = SortType.NONE;
        }
        return ResponseEntity.ok(hotelService.findAll(page, size, sortType));
    }

    @GetMapping("/lessPrice")
    public ResponseEntity<List<HotelResponse>> findLessPrice(
            @RequestParam()
            BigDecimal price
    ){
        return ResponseEntity.ok(hotelService.findLessPrice(price));
    }


    @GetMapping("/betwenPrice")
    public ResponseEntity<List<HotelResponse>> findByBetwenPrice(
            @RequestParam()
            BigDecimal min,
            @RequestParam()
            BigDecimal max
    ){
        return ResponseEntity.ok(hotelService.findBetweenPrice(min,max));
    }

    @GetMapping("/rating")
    public ResponseEntity<List<HotelResponse>> findByRatingGreater(
            @RequestParam()
            Integer ranting
    ){
        return ResponseEntity.ok(hotelService.findByRatingGreaterThan(ranting));
    }
}
