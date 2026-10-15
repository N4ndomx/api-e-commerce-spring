package com.nandomx.travelUno.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest implements Serializable {

    private String customerId;

    @NotNull(message = "Hotel ID is required")
    private Long hotelId;
    // private LocalDateTime dateTimeReservation;
    private LocalDate dateStart;
    // private LocalDate dateEnd;
    private Integer totalDays;

}
