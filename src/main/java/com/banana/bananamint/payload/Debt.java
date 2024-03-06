package com.banana.bananamint.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Debt {

    private Long accountId;

    private LocalDate initDate;
    private LocalDate finalDate;

    private double[] accumulatedDebt; // income - expense, if negative, in the date range, for every month. It is accumulative.

}
