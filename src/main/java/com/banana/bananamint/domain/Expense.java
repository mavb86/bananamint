package com.banana.bananamint.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Integer Id;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer user;

    private double amount;

    private LocalDate dueDate;
    @ManyToOne()
    @JoinColumn(name="account_id")
    private Account moneyFrom;

    private String status;

}
