package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Goal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface GoalJPARepository {
    public List<Goal> findAllByCustomerId(Long idCustomer) throws SQLException;

    public Goal dave(Goal goal) throws SQLException;

    public List<Goal> findByCustomerAndDate(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws SQLException;
}
