package com.nandomx.travelUno.api.models.responses;

import com.nandomx.travelUno.util.AeroLine;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class FlyResponse implements Serializable {

    private Long id;

    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;

    private String destinyName;
    private String originName;

    private BigDecimal price ;

    private AeroLine aeroLine;

}
