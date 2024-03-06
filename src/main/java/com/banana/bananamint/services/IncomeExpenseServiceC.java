package com.banana.bananamint.services;

import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.exception.CustomerException;
import com.banana.bananamint.exception.IncomeExpenseException;
import com.banana.bananamint.payload.IncomeExpenseComparison;
import com.banana.bananamint.persistence.CustomerJPARepository;
import com.banana.bananamint.persistence.IncomeJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class IncomeExpenseServiceC implements IncomeExpenseService{
    @Autowired
    private IncomeJPARepository incomeJPARepository;
    @Autowired
    private CustomerJPARepository customerJPARepository;


    @Override
    public List<Income> showAllIncomes(Long idCustomer) throws IncomeExpenseException {
        return null;
    }

    @Override
    public Income addIncome(Long idCustomer, Income income) throws IncomeExpenseException {
        Customer customer = customerJPARepository.findById(idCustomer).orElseThrow(
                ()-> new CustomerException("Cliente no encontrado"));
        income.setUser(customer);
        incomeJPARepository.save(income);
        income.getMoneyTo().ingresar(income.getAmount());
        return income;
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
        return null;
    }
}
