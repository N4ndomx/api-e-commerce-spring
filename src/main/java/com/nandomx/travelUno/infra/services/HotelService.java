package com.nandomx.travelUno.infra.services;

import com.nandomx.travelUno.api.models.responses.HotelResponse;
import com.nandomx.travelUno.domain.entities.HotelEntity;
import com.nandomx.travelUno.domain.repositories.HotelRepository;
import com.nandomx.travelUno.infra.abstrac_service.IHotelService;
import com.nandomx.travelUno.util.SortType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class HotelService implements IHotelService {
    private HotelRepository repository ;

    @Override
    public Page<HotelResponse> findAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest;
        switch (sortType) {
            case LOWER ->  pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER ->  pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
            default -> pageRequest = PageRequest.of(page, size);
        }

        return repository.findAll(pageRequest).map(this::maptoResponse);
    }

    @Override
    public List<HotelResponse> findLessPrice(BigDecimal price) {

        return repository.findByPriceLessThan(price).stream().map(this::maptoResponse).toList();
    }

    @Override
    public List<HotelResponse> findBetweenPrice(BigDecimal min, BigDecimal max) {
        return repository.findByPriceBetween(min,max).stream().map(this::maptoResponse).toList();

    }

    @Override
    public List<HotelResponse> findByRatingGreaterThan(Integer rating) {
        return repository.findByRatingGreaterThan(rating).stream().map(this::maptoResponse).toList();
    }

    private HotelResponse maptoResponse(HotelEntity entity){
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity, hotelResponse);
        return hotelResponse;
    }
}
