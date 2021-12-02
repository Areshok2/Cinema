package com.exemple.cinema.service;

import com.exemple.cinema.dto.OrderDto;
import com.exemple.cinema.dto.TicketDto;

import java.util.List;

public interface TicketService {
    TicketDto createTicket(TicketDto ticketDto);

    List<TicketDto> getAll();

    TicketDto getTicketById(Long id);

    List<TicketDto> getTicketsByOrderId(Long orderId);

    TicketDto updateTicket(TicketDto ticketDto);

    List<TicketDto> deleteTicketById(Long id);

    OrderDto addTicketToOrder(long orderId, long ticketId);
}
