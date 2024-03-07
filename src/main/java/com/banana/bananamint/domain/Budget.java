package com.banana.bananamint.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "presupuestos")

@Getter
@Setter
@ToString
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "owner_id")

    private Customer owner;
    @Transient
    private Category category;

    private double amount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer user;

    private Long selected;

    private Long balance;


}
