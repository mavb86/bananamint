package com.banana.bananamint.services;

import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Expense;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.exception.CustomerException;
import com.banana.bananamint.exception.IncomeExpenseException;
import com.banana.bananamint.payload.IncomeExpenseComparison;
import com.banana.bananamint.persistence.CustomerJPARepository;
import com.banana.bananamint.persistence.ExpenseJPARepository;
import com.banana.bananamint.persistence.IncomeJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeExpenseServiceC implements IncomeExpenseService {
    @Autowired
    private IncomeJPARepository incomeJPARepository;
    @Autowired
    private CustomerJPARepository customerJPARepository;

    @Autowired
    private ExpenseJPARepository expenseJPARepository;

    @Override
    public List<Income> showAllIncomes(Long idCustomer) throws IncomeExpenseException {
        return null;
    }

    @Override
    public Income addIncome(Long idCustomer, Income income) throws IncomeExpenseException {
        Customer customer = customerJPARepository.findById(idCustomer).orElseThrow(
                () -> new CustomerException("Cliente no encontrado"));
        income.setUser(customer);
        income.getMoneyTo().ingresar(income.getAmount());


        return incomeJPARepository.save(income);
    }

    @Override
    public List<Income> showAllExpenses(Long idCustomer) throws IncomeExpenseException {
        return null;
    }

    @Override
    public Income addExpense(Long idCustomer, Income income) throws IncomeExpenseException {
        return null;
    }

    @Override
    public List<Income> showAllExpensesByDateRange(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws IncomeExpenseException {
        return null;
    }

    @Override
    public List<IncomeExpenseComparison> getFinancialPerspective(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws IncomeExpenseException {
        Customer customer = customerJPARepository.findById(idCustomer).orElseThrow(
                () -> new CustomerException("Cliente no encontrado"));

        List<IncomeExpenseComparison> incomeExpenseComparisonList = new ArrayList<>();
        LocalDate oneDate = initDate;
        while (oneDate.isBefore(finalDate.plusDays(1))) {
            Income incomeAcumuladoDia = acumularIncome(incomeJPARepository.findByUser_idAndEnterDate(idCustomer, oneDate));
            Expense expenseAcumuladoDia = acumularExpense(expenseJPARepository.findByUser_idAndDueDate(idCustomer, oneDate));

            if ((incomeAcumuladoDia != null && incomeAcumuladoDia.getAmount() != 0) ||
                    (expenseAcumuladoDia != null && expenseAcumuladoDia.getAmount() != 0)) {
                IncomeExpenseComparison incomeExpenseComparison = new IncomeExpenseComparison(incomeAcumuladoDia, expenseAcumuladoDia);
                incomeExpenseComparisonList.add(incomeExpenseComparison);
            }
            oneDate = oneDate.plusDays(1);
        }
        if (incomeExpenseComparisonList.size() == 0)
            throw new IncomeExpenseException("Rango sin datos para la perspectiva financiera");

        return incomeExpenseComparisonList;
    }

    public Income acumularIncome(List<Income> lista) {
        Income newIncome = new Income();
        for (Income iIncome : lista) {
            double importe = newIncome.getAmount() + iIncome.getAmount();
            newIncome.setEnterDate(iIncome.getEnterDate());
            newIncome.setAmount(importe);
        }
        return newIncome;
    }

    public Expense acumularExpense(List<Expense> lista) {
        Expense newExpense = new Expense();
        for (Expense iExpense : lista) {
            double importe = newExpense.getAmount() + iExpense.getAmount();
            newExpense.setDueDate(iExpense.getDueDate());
            newExpense.setAmount(importe);
        }
        return newExpense;
    }
}
