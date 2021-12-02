package lpnu.dto;

import lpnu.entity.Ticket;
import lombok.Data;

import java.util.List;
@Data
public class OrderDto {
    private Long id;
    private List<Ticket> tickets;
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String number;
    private double sum;
}
