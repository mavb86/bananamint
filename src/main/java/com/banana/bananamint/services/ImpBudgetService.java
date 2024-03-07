package com.banana.bananamint.services;


import com.banana.bananamint.domain.Budget;
import com.banana.bananamint.exception.BudgetException;
import com.banana.bananamint.persistence.BudgetJPARepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service
public class ImpBudgetService implements BudgetService {
    //private Logger logger = LoggerFactory.getLogger(BudgetService.class);

    @Autowired
    private BudgetJPARepository budgetRepository;

    @Override
    public List<Budget> showAll(Long idCustomer, String categoryName) throws BudgetException {
        return null;
    }

    @Override
    public Budget add(Long idCustomer, String categoryName) throws BudgetException {
        return null;
    }
}