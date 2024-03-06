package com.banana.bananamint.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Integer id;

    private double amount;

    private String description;

    private String paymentType;

    private Category category;

    private Account account;

    private Customer user;

    private boolean isDeleted;

    private Long dateTime;

    private LocalDate createdAt;

    private LocalDate updatedAt;


}
