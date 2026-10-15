package com.nandomx.travelUno.infra.abstrac_service;

import com.nandomx.travelUno.api.models.requests.TicketRequest;
import com.nandomx.travelUno.api.models.responses.TicketResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITicketService extends ICrudService<TicketRequest, TicketResponse, UUID> {
BigDecimal findPrice(Long flyId);

}
