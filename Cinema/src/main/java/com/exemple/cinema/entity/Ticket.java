package com.exemple.cinema.entity;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Ticket {
    private Long id;
    private String filmName;
    private String description;
    private LocalDate dateOfPerformance;
    private double price;
}
