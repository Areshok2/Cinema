package com.exemple.cinema.dto;

import com.exemple.cinema.entity.Ticket;
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
