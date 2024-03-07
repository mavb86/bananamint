package com.banana.bananamint.services;

import com.banana.bananamint.domain.Budget;
import com.banana.bananamint.exception.BudgetException;
import com.banana.bananamint.persistence.BudgetJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public interface BudgetService {

    public List<Budget> showAll(Long idCustomer, String categoryName) throws BudgetException;

    public Budget add(Long idCustomer, String categoryName) throws BudgetException;


}
