package com.banana.bananamint.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    private Long id;

    private Category category;

    private double amount;

    private Customer user;

    private Long selected;

    private Long balance;

}
