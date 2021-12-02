package com.exemple.cinema.resource;

import com.exemple.cinema.dto.OrderDto;
import com.exemple.cinema.dto.TicketDto;
import com.exemple.cinema.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TicketResource {
    private final TicketService ticketService;

    public TicketResource(final TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/ticket/")
    public TicketDto createTicket(@RequestBody final TicketDto ticketDto) {
        return ticketService.createTicket(ticketDto);
    }

    @GetMapping("/ticket/{id}")
    public TicketDto getTicketById(@PathVariable final Long id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/ticket/order_id/{id}")
    public List<TicketDto> getTicketsByOrderId(@PathVariable final Long id) {
        return ticketService.getTicketsByOrderId(id);
    }

    @GetMapping("/ticket/")
    public List<TicketDto> getAll() {
        return ticketService.getAll();
    }

    @GetMapping("/ticket/addToOrder/{orderId}/ticket/{ticketId}")
    public OrderDto addTicketToOrder(@PathVariable final Long orderId, @PathVariable final Long ticketId){
        return ticketService.addTicketToOrder(orderId, ticketId);
    }

    @PutMapping("/ticket/")
    public TicketDto updateTicket(@RequestBody final TicketDto ticketDto) {
        return ticketService.updateTicket(ticketDto);
    }

    @DeleteMapping("/ticket/{id}")
    public List<TicketDto> deleteTicket(@PathVariable final Long id) {
        return ticketService.deleteTicketById(id);
    }
}
