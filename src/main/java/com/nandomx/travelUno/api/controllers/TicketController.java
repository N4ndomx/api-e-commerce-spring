package com.nandomx.travelUno.api.controllers;

import com.nandomx.travelUno.api.models.requests.TicketRequest;
import com.nandomx.travelUno.api.models.responses.TicketResponse;
import com.nandomx.travelUno.infra.abstrac_service.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "ticket")
@AllArgsConstructor
public class TicketController {
    private final ITicketService ticketService;

    // Siempre usar ResponseEntity
    @PostMapping
    public ResponseEntity<TicketResponse> post(@RequestBody() TicketRequest ticketRequest) {
        return ResponseEntity.ok(ticketService.create(ticketRequest));
    }

    @GetMapping()
    public ResponseEntity<Map<String, BigDecimal>> getFlyPrice(@RequestParam(name = "flyId") long flyId) {
        System.out.println(flyId);
        return ResponseEntity.ok(Collections.singletonMap(
                "Vuelo:" + flyId, ticketService.findPrice(flyId)));
    }

    @GetMapping(path = "{ticketId}")
    public ResponseEntity<TicketResponse> get(@PathVariable UUID ticketId) {
        return ResponseEntity.ok(ticketService.read(ticketId));
    }

    @PutMapping(path = "{ticketId}")
    public ResponseEntity<TicketResponse> put(@PathVariable UUID ticketId, @RequestBody() TicketRequest ticketRequest) {
        return ResponseEntity.ok(ticketService.update(ticketRequest, ticketId));
    }

    @DeleteMapping(path = "{ticketId}")
    public ResponseEntity<Void> delete(@PathVariable UUID ticketId) {
        ticketService.delete(ticketId);
        return ResponseEntity.noContent().build();
    }

}
