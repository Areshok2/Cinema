package lpnu.mapper;

import lpnu.dto.OrderDto;
import lpnu.entity.Order;
import lpnu.entity.User;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto toDTO(final Order order, final User user) {
        final OrderDto orderDTO = new OrderDto();

        orderDTO.setId(order.getId());
        orderDTO.setTickets(order.getTickets());
        orderDTO.setSum(order.getSum());

        if (user != null) {
            orderDTO.setUserId(user.getId());
            orderDTO.setFirstname(user.getFirstname());
            orderDTO.setLastname(user.getLastname());
            orderDTO.setNumber(user.getNumber());
            orderDTO.setEmail(user.getEmail());
        }

        return orderDTO;
    }

    public Order toEntity(final OrderDto orderDTO) {
        final Order order = new Order();

        order.setId(orderDTO.getId());
        order.setTickets(orderDTO.getTickets());
        order.setSum(orderDTO.getSum());
        order.setUserId(orderDTO.getUserId());

        return order;
    }
}