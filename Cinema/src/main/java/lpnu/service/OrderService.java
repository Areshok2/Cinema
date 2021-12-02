package lpnu.service;

import lpnu.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> getAllByUserId(Long userId);

    List<OrderDto> getAll();

    OrderDto getById(Long orderId);

    OrderDto updateOrder(OrderDto orderDto);
}
