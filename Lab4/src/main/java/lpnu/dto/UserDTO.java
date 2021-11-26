package lpnu.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class UserDTO {
    private Long id;
    @NotNull
    private String firstname;
    private String lastname;
    private LocalDate day;

    public UserDTO() {
    }

    public UserDTO(final Long id, final String firstname, final String lastname, final LocalDate day) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.day = day;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(final LocalDate day) {
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
//dto without logic