package com.exemple.cinema.mapper;

import com.exemple.cinema.dto.TicketDto;
import com.exemple.cinema.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public Ticket toEntity(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setFilmName(ticketDto.getFilmName());
        ticket.setPrice(ticketDto.getPrice());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setDateOfPerformance(ticketDto.getDateOfPerformance());

        return ticket;
    }

    public TicketDto toDTO(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setFilmName(ticket.getFilmName());
        ticketDto.setPrice(ticket.getPrice());
        ticketDto.setDescription(ticket.getDescription());
        ticketDto.setDateOfPerformance(ticket.getDateOfPerformance());

        return ticketDto;
    }
}
