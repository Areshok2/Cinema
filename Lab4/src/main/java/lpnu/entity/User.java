package lpnu.entity;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    @NotNull
    private String firstname;
    private String lastname;
    private LocalDate day;

    private List<Car> cars = new ArrayList<>();

    public User() {
    }

    public User(final Long id, final String firstname, final String lastname, final LocalDate day, final List<Car> orders) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.day = day;
        this.cars = orders;
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

    public List<Car> getOrders() {
        return cars;
    }

    public void setOrders(final List<Car> orders) {
        this.cars = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
