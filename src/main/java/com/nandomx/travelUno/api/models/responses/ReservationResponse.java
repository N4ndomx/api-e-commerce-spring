package com.nandomx.travelUno.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponse implements Serializable {

    public UUID id;
    public LocalDateTime dateTimeReservation; // Esto es un timestap
    public LocalDate dateStart; // es Date en bd
    public LocalDate dateEnd;  // es Date en bd
    public Integer totalDays;
    public BigDecimal price ;
    public HotelResponse hotel;
}
