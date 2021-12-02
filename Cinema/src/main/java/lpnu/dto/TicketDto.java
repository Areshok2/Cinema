package lpnu.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
public class TicketDto {
    private Long id;

    @NotBlank
    private String filmName;
    @NotBlank
    private String description;
    private LocalDate dateOfPerformance;

    @Min(1)
    @Max(200)
    @NotNull
    private double price;
}
