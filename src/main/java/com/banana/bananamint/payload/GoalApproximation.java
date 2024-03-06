package com.banana.bananamint.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalApproximation {

    private Goal goal;

    private double targetAmountDifference;

    private int tendency; // 1, 0, -1, depending on targetAmountDifference (positive, near zero, negative)

    private LocalDate estimatedReachingTargetDate;

}
