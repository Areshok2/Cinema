package com.exemple.cinema.service.Impl;

import com.exemple.cinema.dto.OrderDto;
import com.exemple.cinema.entity.Order;
import com.exemple.cinema.entity.User;
import com.exemple.cinema.mapper.OrderMapper;
import com.exemple.cinema.repository.OrderRepository;
import com.exemple.cinema.repository.UserRepository;
import com.exemple.cinema.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        User user = userRepository.getById(orderDto.getUserId());
        Order order = orderMapper.toEntity(orderDto);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toDTO(savedOrder, user);
    }

    @Override
    public List<OrderDto> getAllByUserId(Long userId) {
        User user = userRepository.getById(userId);
        return orderRepository.getByUserId(userId).stream()
                .map((order) -> orderMapper.toDTO(order, user))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.getAll().stream()
                .map(order -> orderMapper.toDTO(order, userRepository.getById(order.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getById(Long orderId) {
        Order order = orderRepository.getById(orderId);
        User user = userRepository.getById(order.getId());

        return orderMapper.toDTO(order, user);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order updatedOrder = orderRepository.update(order);

        return orderMapper.toDTO(updatedOrder, userRepository.getById(updatedOrder.getId()));
    }
}
