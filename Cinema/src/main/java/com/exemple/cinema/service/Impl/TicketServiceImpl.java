package com.exemple.cinema.service.Impl;

import com.exemple.cinema.dto.OrderDto;
import com.exemple.cinema.dto.TicketDto;
import com.exemple.cinema.entity.Order;
import com.exemple.cinema.entity.Ticket;
import com.exemple.cinema.entity.User;
import com.exemple.cinema.exception.ServiceException;
import com.exemple.cinema.mapper.OrderMapper;
import com.exemple.cinema.mapper.TicketMapper;
import com.exemple.cinema.repository.OrderRepository;
import com.exemple.cinema.repository.TicketRepository;
import com.exemple.cinema.repository.UserRepository;
import com.exemple.cinema.service.OrderService;
import com.exemple.cinema.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public TicketServiceImpl(final TicketRepository ticketRepository, final TicketMapper ticketMapper, final OrderService orderService, final OrderRepository orderRepository, final UserRepository userRepository, final OrderMapper orderMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public TicketDto createTicket(final TicketDto ticketDto){
        final Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    @Override
    public List<TicketDto> getAll(){
      return ticketRepository.getAll().stream().map(ticketMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TicketDto getTicketById(final Long id){
        return ticketMapper.toDTO(ticketRepository.getById(id));
    }

    @Override
    public List<TicketDto> getTicketsByOrderId(final Long orderId){
        final OrderDto orderDto = orderService.getById(orderId);
        final List<Ticket> ticketList = orderDto.getTickets();
        if (ticketList==null){
            throw new ServiceException(400,"Ticket list is empty",null);
        }
        return ticketList.stream().map(ticketMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TicketDto updateTicket(final TicketDto ticketDto){
        final Ticket ticket = ticketMapper.toEntity(ticketDto);
        return ticketMapper.toDTO(ticketRepository.update(ticket));
    }

    @Override
    public List<TicketDto> deleteTicketById(final Long id){
        final List<Ticket> newTicketList = ticketRepository.delete(id);
        return newTicketList.stream().map(ticketMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDto addTicketToOrder(final long orderId, final long ticketId) {
        final Order order = orderRepository.getById(orderId);
        final Ticket ticket = ticketRepository.getById(ticketId);
        final User user = userRepository.getById(order.getUserId());
        List<Ticket> tickets = order.getTickets();
        if (tickets == null) {
            tickets = new ArrayList<>();
        }
        tickets.add(ticket);
        order.setTickets(tickets);
        order.setSum(tickets.stream().map(Ticket::getPrice).reduce(0.0, Double::sum));
        return orderMapper.toDTO(orderRepository.update(order), user);
    }
}
