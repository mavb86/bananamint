package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Expense;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseJPARepository {
    public List<Expense> findAll(Long idCustomer) throws SQLException;
    public List<Expense> findAllByDate(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws SQLException;

    public Expense save(Expense expense) throws SQLException;
}
