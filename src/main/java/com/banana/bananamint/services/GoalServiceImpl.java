package com.banana.bananamint.services;

import com.banana.bananamint.domain.Goal;
import com.banana.bananamint.domain.GoalApproximation;
import com.banana.bananamint.exception.GoalException;
import com.banana.bananamint.payload.Debt;
import com.banana.bananamint.persistence.GoalJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {
    @Autowired
    GoalJPARepository repo;

    @Override
    public List<Goal> showAll(Long idCustomer) throws GoalException, SQLException {
       return repo.findAllByUser_Id(idCustomer);
    }

    @Override
    public List<Goal> add(Long idCustomer, Goal goal) throws GoalException, SQLException {
        repo.save(goal);
        return repo.findAllByUser_Id(idCustomer);
    }

    @Override
    public List<GoalApproximation> generateReport(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws GoalException {
        return null;
    }

    @Override
    public List<Debt> accumulatedDebt(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws GoalException {
        return null;
    }
}
