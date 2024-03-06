package com.banana.bananamint.services;

import com.banana.bananamint.domain.Budget;
import com.banana.bananamint.exception.BudgetException;

import java.util.List;

public interface BudgetService {
    public List<Budget> showAll(Long idCustomer, String categoryName) throws BudgetException;

    public Budget add(Long idCustomer, String categoryName) throws BudgetException;
}
