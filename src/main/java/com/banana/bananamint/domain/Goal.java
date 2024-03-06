package com.banana.bananamint.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goal {
    private Long id;

    private String name;

    private String description;

    private double targetAmount;

    private String status;

    private LocalDate targetDate;

    private Customer user;


}
