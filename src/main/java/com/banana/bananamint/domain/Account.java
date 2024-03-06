package com.banana.bananamint.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    private String type;

    LocalDate openingDate;

    private double balance;

    private double maxOverdraft;
    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnore
    private Customer owner;

    private boolean active;
    public void ingresar (Double importe) {
        this.balance = this.balance + importe;
    }
}
