package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lpnu.entity.Ticket;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private List<Ticket> tickets;
    private Long userId;

    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Email
    private String email;
    @NotBlank
    private String number;
    @NotNull
    private double sum;
}
