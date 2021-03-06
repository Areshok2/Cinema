package lpnu.service.Impl;

import lpnu.dto.OrderDto;
import lpnu.entity.Order;
import lpnu.entity.User;
import lpnu.exception.ServiceException;
import lpnu.mapper.OrderMapper;
import lpnu.repository.OrderRepository;
import lpnu.repository.UserRepository;
import lpnu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderMapper orderMapper;
    @Autowired
    private final UserRepository userRepository;

    public OrderServiceImpl(final OrderRepository orderRepository, final OrderMapper orderMapper, final UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
    }

    @Override
    public OrderDto createOrder(final OrderDto orderDto) {

        if(orderDto.getId() != null){
            throw new ServiceException(400, "Order shouldn't have an id ", null);
        }

        final User user = userRepository.getById(orderDto.getUserId());
        final Order order = orderMapper.toEntity(orderDto);
        final Order savedOrder = orderRepository.save(order);

        return orderMapper.toDTO(savedOrder, user);
    }

    @Override
    public List<OrderDto> getAllByUserId(final Long userId) {
        final User user = userRepository.getById(userId);

        return orderRepository.getByUserId(userId).stream()
                .map((e) -> orderMapper.toDTO(e, user))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.getAll().stream()
                .map(e -> orderMapper.toDTO(e, userRepository.getById(e.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getById(final Long orderId) {
        final Order order = orderRepository.getById(orderId);
        final User user = userRepository.getById(order.getId());

        return orderMapper.toDTO(order, user);
    }

    @Override
    public OrderDto updateOrder(final OrderDto orderDto) {

        if(orderDto.getId() == null){
            throw new ServiceException(400, "Order doesn't have an id ", null);
        }

        final Order order = orderMapper.toEntity(orderDto);
        final Order updatedOrder = orderRepository.update(order);

        return orderMapper.toDTO(updatedOrder, userRepository.getById(updatedOrder.getId()));
    }
}
