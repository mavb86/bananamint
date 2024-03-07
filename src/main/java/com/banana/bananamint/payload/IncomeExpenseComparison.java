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

    public int calcularPerspectiva (Income income, Expense expense) {
        double incomeAmount;
        if (income != null) incomeAmount = income.getAmount();
        else incomeAmount = 0;
        double expenseAmount;
        if (expense !=null) expenseAmount = expense.getAmount();
        else expenseAmount = 0;

        Double dif = incomeAmount - expenseAmount;
        if (dif > 0 ) this.perspective = 1;
        else if (dif < 0) this.perspective = -1;
        else this.perspective = 0;
        return this.perspective;
    }

    public IncomeExpenseComparison(Income income, Expense expense) {
        this.income = income;
        this.expense = expense;
        calcularPerspectiva(income, expense);
    }
}
