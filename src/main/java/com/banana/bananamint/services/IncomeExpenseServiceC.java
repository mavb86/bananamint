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
public class IncomeExpenseServiceC implements IncomeExpenseService{
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
                ()-> new CustomerException("Cliente no encontrado"));
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
        List<IncomeExpenseComparison> incomeExpenseComparisonList = new ArrayList<>();
        //List<Income> incomeList = incomeJPARepository.
        List<Income> incomeList = incomeJPARepository.findByUser_id(idCustomer);
        List<Expense> expenseList = expenseJPARepository.findByUser_id(idCustomer);


        return incomeExpenseComparisonList;
    }
}
