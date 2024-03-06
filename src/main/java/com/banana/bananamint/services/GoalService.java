package com.banana.bananamint.services;

import com.banana.bananamint.payload.Debt;
import com.banana.bananamint.domain.Goal;
import com.banana.bananamint.domain.GoalApproximation;
import com.banana.bananamint.exception.GoalException;

import java.time.LocalDate;
import java.util.List;

public interface GoalService {
    public List<Goal> showAll(Long idCustomer) throws GoalException;

    public List<Goal> add(Long idCustomer, Goal goal) throws GoalException;

    public List<GoalApproximation> generateReport(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws GoalException;

    public List<Debt> accumulatedDebt(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws GoalException;

}
