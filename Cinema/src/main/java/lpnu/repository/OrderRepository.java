package lpnu.repository;

import lpnu.entity.Order;

import lpnu.exception.ServiceException;
import lpnu.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    private List<Order> orderList;
    private Long lastId = 0L;

    @PostConstruct
    public void init(){

        final Path file = Paths.get("order.txt");
        try {
            final String savedUsersAsString = Files.readString(file, StandardCharsets.UTF_16);
            orderList = Util.deserialize(savedUsersAsString, new TypeReference<List<Order>>() {});


            if (orderList == null) {
                orderList = new ArrayList<>();
                return;
            }

            final long maxId = orderList.stream().mapToLong(Order::getId).max().orElse(1);

            lastId = maxId + 1;

        } catch (final Exception e){
            System.out.println("We have an issue");
            orderList = new ArrayList<>();
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("order.txt");
        try {
            Files.writeString(file, Util.serialize(orderList), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }

    public Order getById(final Long id) {
        return orderList.stream()
                .filter((e -> e.getId().equals(id)))
                .findFirst()
                .orElseThrow(()-> new ServiceException(400,"Order with id: " + id + " not found",null));
    }

    public List<Order> getAll() {
        return orderList;
    }

    public Order save(final Order order) {
        if(order.getId() != null){
            throw new ServiceException(400, "Order shouldn't have an id ", null);
        }

        ++lastId;
        order.setId(lastId);
        orderList.add(order);
        return order;
    }

    public Order update(final Order order) {

        if (order.getId() == null){
            throw new ServiceException(400,"Order shouldn't have an id", null);
        }

        final Order oldOrder = getById(order.getId());

        orderList.remove(oldOrder);
        orderList.add(order);
        return order;
    }

    public List<Order> getByUserId(final Long userId) {
        return orderList.stream().filter((o) -> o.getUserId().equals(userId)).collect(Collectors.toList());
    }
}
