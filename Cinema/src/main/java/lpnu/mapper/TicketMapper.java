package lpnu.mapper;

import lpnu.dto.TicketDto;
import lpnu.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public Ticket toEntity(final TicketDto ticketDto) {
        final Ticket ticket = new Ticket();

        ticket.setId(ticketDto.getId());
        ticket.setFilmName(ticketDto.getFilmName());
        ticket.setPrice(ticketDto.getPrice());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setDate(ticketDto.getDate());

        return ticket;
    }

    public TicketDto toDTO(final Ticket ticket) {
        final TicketDto ticketDto = new TicketDto();

        ticketDto.setId(ticket.getId());
        ticketDto.setFilmName(ticket.getFilmName());
        ticketDto.setPrice(ticket.getPrice());
        ticketDto.setDescription(ticket.getDescription());
        ticketDto.setDate(ticket.getDate());

        return ticketDto;
    }
}
