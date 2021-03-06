package lpnu.resource;

import lpnu.dto.OrderDto;
import lpnu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderResource {

    @Autowired
    private final OrderService orderService;

    public OrderResource(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/")
    public OrderDto createOrder(@RequestBody final OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/order/{id}")
    public OrderDto getById(@PathVariable final Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/order/")
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/order/user/{userId}")
    public List<OrderDto> getAllByUserId(@PathVariable final Long userId) {
        return orderService.getAllByUserId(userId);
    }

    @PutMapping("/order/")
    public OrderDto updateOrder(@RequestBody final OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }
}