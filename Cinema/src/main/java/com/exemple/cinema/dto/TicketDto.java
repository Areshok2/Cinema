package com.exemple.cinema.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TicketDto {
    private Long id;
    private String filmName;
    private String description;
    private LocalDate dateOfPerformance;
    private double price;
}
