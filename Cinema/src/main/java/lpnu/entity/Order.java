package lpnu.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class Order {
    private Long id;
    private List<Ticket> tickets;
    private Long userId;

    @NotNull
    private double sum;
}
