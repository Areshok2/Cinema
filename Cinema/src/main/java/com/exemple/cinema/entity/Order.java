package com.exemple.cinema.entity;

import lombok.Data;

import java.util.List;
@Data
public class Order {
    private Long id;
    private List<Ticket> tickets;
    private Long userId;
    private double sum;
}
