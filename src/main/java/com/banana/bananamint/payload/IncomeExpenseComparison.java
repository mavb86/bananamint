package com.banana.bananamint.payload;


import com.banana.bananamint.domain.Expense;
import com.banana.bananamint.domain.Income;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeExpenseComparison {

    private Income income;
    private Expense expense;

    private int perspective; // 1, 0, -1, depending on income - expense (positive, near zero, negative)

}
