package com.banana.bananamint.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;

    private String type;

    LocalDate openingDate;

    private double balance;

    private double maxOverdraft;

    private Customer owner;

    private boolean active;

}
