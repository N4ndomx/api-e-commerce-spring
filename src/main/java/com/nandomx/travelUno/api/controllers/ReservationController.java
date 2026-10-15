package com.nandomx.travelUno.api.controllers;

import com.nandomx.travelUno.api.models.requests.ReservationRequest;
import com.nandomx.travelUno.api.models.responses.ReservationResponse;
import com.nandomx.travelUno.infra.abstrac_service.IReservationService;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
@RequestMapping("reservation")
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping()
    public ResponseEntity<ReservationResponse> postReservation(@RequestBody ReservationRequest reservationRequest) {
        var result = this.reservationService.create(reservationRequest);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.GET, path = "{reservationId}")
    public ResponseEntity<ReservationResponse> getReservation(
            @PathVariable @Nonnull @Validated UUID reservationId) {
        var result = this.reservationService.read(reservationId);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponse> putResevation(@PathVariable UUID id,
            @Valid @RequestBody ReservationRequest entity) {

        var result = this.reservationService.update(entity, id);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable UUID id) {
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
