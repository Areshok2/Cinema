package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Long id;

    @NotBlank
    private String filmName;
    @NotBlank
    private String description;
    private LocalDate date;
    @Min(1)
    @Max(200)
    @NotNull
    private double price;
}
