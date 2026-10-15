package com.nandomx.travelUno.infra.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.nandomx.travelUno.api.models.responses.FlyResponse;
import com.nandomx.travelUno.domain.entities.FlyEntity;
import com.nandomx.travelUno.domain.repositories.FlyRepository;
import com.nandomx.travelUno.infra.abstrac_service.IFlyService;
import com.nandomx.travelUno.util.SortType;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FlyService implements IFlyService {
    private final FlyRepository flyRepository;

    @Override
    public List<FlyResponse> findByOriginDestiny(String origin, String destiny) {

        return flyRepository.selectOriginDestiny(origin, destiny).stream().map(this::mapToResponse).toList();
    }

    @Override
    public Page<FlyResponse> findAll(Integer page, Integer size, SortType sortType) {
        // Implementation here
        PageRequest pageRequest;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
            default -> pageRequest = PageRequest.of(page, size);
        }
        return flyRepository.findAll(pageRequest).map(this::mapToResponse);
    }

    @Override
    public List<FlyResponse> findLessPrice(BigDecimal price) {
        var fly = flyRepository.selectLessPrice(price);

        return fly.stream().map(entity -> this.mapToResponse(entity)).toList();
    }

    @Override
    public List<FlyResponse> findBetweenPrice(BigDecimal min, BigDecimal max) {
        return flyRepository.selectBetweenPrice(min,max).stream().map(entity -> this.mapToResponse(entity)).toList();
    }

    private FlyResponse mapToResponse(FlyEntity entity) {
        // Mapping logic here
        var response = new FlyResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

}
